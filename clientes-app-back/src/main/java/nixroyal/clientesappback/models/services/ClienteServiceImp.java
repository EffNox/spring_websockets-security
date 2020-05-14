package nixroyal.clientesappback.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nixroyal.clientesappback.models.dao.ClienteDao;
import nixroyal.clientesappback.models.dao.FacturaDao;
import nixroyal.clientesappback.models.dao.ProductoDao;
import nixroyal.clientesappback.models.entity.Cliente;
import nixroyal.clientesappback.models.entity.Factura;
import nixroyal.clientesappback.models.entity.Producto;
import nixroyal.clientesappback.models.entity.Region;

@Service
public class ClienteServiceImp implements IClienteService {

    private @Autowired ClienteDao rp;

    private @Autowired FacturaDao rpFac;

    private @Autowired ProductoDao rpPro;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) rp.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return rp.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return rp.save(cliente);
    }

    @Override
    public void delete(Long id) {
        rp.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {
        return rp.findAllRegiones();
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return rpFac.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {
        return rpFac.save(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        rpFac.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByProductoByNombre(String nombre) {
        return rpPro.findByNombreContainingIgnoreCase(nombre);
    }

}
