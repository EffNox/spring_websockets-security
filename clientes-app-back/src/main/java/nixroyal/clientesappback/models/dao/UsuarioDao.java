package nixroyal.clientesappback.models.dao;

import org.springframework.data.repository.CrudRepository;
import nixroyal.clientesappback.models.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario,Long> {
    
    public Usuario findByUsername(String username);

    // @Query("select u from Usuario u where u.username=?1")
    // public Usuario findByUsername2(String username);
    
}