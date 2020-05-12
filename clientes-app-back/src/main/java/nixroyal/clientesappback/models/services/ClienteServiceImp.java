package nixroyal.clientesappback.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import nixroyal.clientesappback.models.dao.ClienteDao;
import nixroyal.clientesappback.models.entity.Cliente;
import nixroyal.clientesappback.models.entity.Region;

@Service
public class ClienteServiceImp implements IClienteService {

    private @Autowired ClienteDao rp;

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) rp.findAll();
    }

    // @Override
    // public List<Region> findRegiones() {
    // return (List<Region>) rp.findAllRegions();
    // }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
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
    public List<Region> findAllRegiones() {
        return rp.findAllRegiones();
    }

}
