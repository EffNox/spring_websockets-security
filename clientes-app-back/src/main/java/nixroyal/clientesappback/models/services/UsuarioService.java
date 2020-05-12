package nixroyal.clientesappback.models.services;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nixroyal.clientesappback.models.dao.UsuarioDao;
import nixroyal.clientesappback.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService,UserDetailsService {

    private Logger lg = LoggerFactory.getLogger(UsuarioService.class);
    private @Autowired UsuarioDao dao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = dao.findByUsername(username);

        if (u == null) {
            lg.error("Error en el login: no existe el usuario: ["+username+"] en el sistema");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario: ["+username+"] en el sistema");
        }

        List<GrantedAuthority> authorities =  u.getPerfil().stream()
        .map(perfil->new SimpleGrantedAuthority(perfil.getNombre()))
        .peek(authority-> lg.info(authority.getAuthority())).collect(Collectors.toList());
        
        return new User(u.getUsername(), u.getPassword(), u.getState(), true,true,true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return dao.findByUsername(username);
    }
    
}
