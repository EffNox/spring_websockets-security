package nixroyal.clientesappback.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import nixroyal.clientesappback.models.entity.Factura;
import nixroyal.clientesappback.models.entity.Producto;
import nixroyal.clientesappback.models.services.IClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
public @RequestMapping("/factura") class CFactura {

    private @Autowired IClienteService sv;
    
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{id}")
    public Factura get(@PathVariable Long id) {
        return sv.findFacturaById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sv.deleteFacturaById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/producto")
    public List<Producto> getProductos(@RequestParam(value = "") String n) {
        return sv.findByProductoByNombre(n);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Factura create(@RequestBody Factura m){
        return sv.saveFactura(m);
    } 
    
}
