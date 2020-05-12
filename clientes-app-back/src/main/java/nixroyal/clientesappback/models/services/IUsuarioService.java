package nixroyal.clientesappback.models.services;

import nixroyal.clientesappback.models.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);

}
