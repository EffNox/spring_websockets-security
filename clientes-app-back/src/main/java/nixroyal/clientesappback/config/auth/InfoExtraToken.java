package nixroyal.clientesappback.config.auth;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import nixroyal.clientesappback.models.entity.Usuario;
import nixroyal.clientesappback.models.services.IUsuarioService;

@Component
public class InfoExtraToken implements TokenEnhancer {

    private @Autowired IUsuarioService sv_usu;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuario u = sv_usu.findByUsername(authentication.getName());
        Map<String, Object> infEx = new HashMap<>();
        infEx.put("extra_info", u.getNombre() + "/" + u.getApellido() + "/" + u.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infEx);
        return accessToken;
    }

}
