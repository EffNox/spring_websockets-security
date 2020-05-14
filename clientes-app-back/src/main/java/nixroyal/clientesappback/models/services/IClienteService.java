package nixroyal.clientesappback.models.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import nixroyal.clientesappback.models.entity.Cliente;
import nixroyal.clientesappback.models.entity.Factura;
import nixroyal.clientesappback.models.entity.Producto;
import nixroyal.clientesappback.models.entity.Region;

public interface IClienteService {
    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findById(Long id);

    public Cliente save(Cliente cliente);

    public void delete(Long id);

    public List<Region> findAllRegiones();

    public Factura findFacturaById(Long id);

    public Factura saveFactura(Factura factura);

    public void deleteFacturaById(Long id);

    public List<Producto> findByProductoByNombre(String nombre);
}
