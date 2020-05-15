package nixroyal.chat.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nixroyal.chat.models.documents.Mensaje;
import nixroyal.chat.models.repository.RChat;

@Service
public class SChatImp implements SChat {

    private @Autowired RChat rp;

    @Override
    public List<Mensaje> getLast10Messages() {
        return rp.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje save(Mensaje m) {
        return rp.save(m);
    }
    
}
