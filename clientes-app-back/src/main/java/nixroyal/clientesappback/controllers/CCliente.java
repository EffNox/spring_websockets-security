package nixroyal.clientesappback.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import nixroyal.clientesappback.models.entity.Cliente;
import nixroyal.clientesappback.models.entity.Region;
import nixroyal.clientesappback.models.services.IClienteService;
import nixroyal.clientesappback.models.services.IUploadFileService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:4200"/* , methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE } */)
public @RequestMapping("/cliente") class CCliente {

    private @Autowired IClienteService sv;
    private @Autowired IUploadFileService sv_upl;

    public @GetMapping List<Cliente> getAll() {
        return sv.findAll();
    }

    public @GetMapping("/page") Page<Cliente> getPage(@RequestParam(defaultValue = "0") Integer p) {
        return sv.findAll(PageRequest.of(p, 4));
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public @GetMapping("/{id}") ResponseEntity<?> get(@PathVariable Long id) {
        Map<String, Object> rs = new HashMap<>();
        Cliente cliente = null;
        try {
            cliente = sv.findById(id);
        } catch (DataAccessException e) {
            rs.put("msj", "Error al realizar la consulta en la base de datos");
            rs.put("err", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cliente == null) {
            rs.put("msj", "El cliente ID: " + id.toString() + " no existe en la base de datos");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cliente);
    }

    @Secured("ROLE_ADMIN")
    public @PostMapping ResponseEntity<?> create(@Valid @RequestBody Cliente m, BindingResult rdr) {
        Cliente cliente = null;
        Map<String, Object> rs = new HashMap<>();
        if (rdr.hasErrors()) {
            List<String> errors = rdr.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            rs.put("err", errors);
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }
        try {
            cliente = sv.save(m);
        } catch (DataAccessException e) {
            rs.put("msj", "Error al registrar en la base de datos");
            rs.put("err", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        rs.put("msj", "El cliente ha sido creado con éxito!");
        rs.put("dt", cliente);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    public @PutMapping("/{id}") ResponseEntity<?> update(@Valid @RequestBody Cliente m, BindingResult rdr,
            @PathVariable Long id) {
        Map<String, Object> rs = new HashMap<>();
        if (rdr.hasErrors()) {
            List<String> errors = rdr.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            rs.put("err", errors);
            return ResponseEntity.badRequest().body(rs);
            // return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }
        Cliente clienteActual = sv.findById(id);
        if (clienteActual == null) {
            rs.put("msj",
                    "Error: no se pudo actualizar, el cliente ID: " + id.toString() + " no existe en la base de datos");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
        Cliente clienteUpdated = null;
        try {
            clienteActual.setNombre(m.getNombre());
            clienteActual.setApellido(m.getApellido());
            clienteActual.setEmail(m.getEmail());
            clienteActual.setCreateAt(m.getCreateAt());
            clienteActual.setRegion(m.getRegion());
            clienteUpdated = sv.save(clienteActual);
        } catch (DataAccessException e) {
            rs.put("msj", "Error al actualizar en la base de datos");
            rs.put("err", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        rs.put("msj", "El cliente ha sido actualizado con éxito!");
        rs.put("dt", clienteUpdated);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    public @DeleteMapping("/{id}") ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> rs = new HashMap<>();
        try {
            Cliente cliente = sv.findById(id);
            
        if (cliente == null) {
            rs.put("msj", "El cliente ID: " + id.toString() + " no existe en la base de datos");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }

            String fileNameOld = cliente.getFoto();
            sv_upl.deleteFile(fileNameOld);

            sv.delete(id);
        } catch (DataAccessException e) {
            rs.put("msj", "Error al eliminar en la base de datos");
            rs.put("err", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        rs.put("msj", "El cliente ha sido eliminado con éxito!");
        return ResponseEntity.ok(rs);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public @PostMapping("/upload") ResponseEntity<?> upload(@RequestParam MultipartFile file, @RequestParam Long id) {
        Map<String, Object> rs = new HashMap<>();
        Cliente cliente = sv.findById(id);
        if (cliente == null) {
            rs.put("msj", "Error: el cliente ID: " + id.toString() + " no existe en la base de datos");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
        if (!file.isEmpty()) {
            String fileName = null;
            try {
                fileName = sv_upl.saveFile(file);
            } catch (IOException e) {
                rs.put("err", e.getMessage() + ": " + e.getCause().getMessage());
                rs.put("msj", "Error al subir el archivo");
                return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String fileNameOld = cliente.getFoto();
            sv_upl.deleteFile(fileNameOld);

            cliente.setFoto(fileName);
            sv.save(cliente);
            rs.put("dt", cliente);
            rs.put("msj", "Se ha subido correctamente la imágen: " + fileName);
        }
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    public @GetMapping("/upload/{fileName:.+}") ResponseEntity<?> viewFile(@PathVariable String fileName) {
        // public ResponseEntity<?> viewFile(@RequestParam String fileName) {
        Resource recurso = null;
        try {
            recurso = sv_upl.getFile(fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add("Content-Disposition", "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    public @GetMapping("/regiones") List<Region> getRegiones() {
        return sv.findAllRegiones();
    }
}
