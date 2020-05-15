package nixroyal.chat.models.services;

import java.util.List;
import nixroyal.chat.models.documents.Mensaje;

public interface SChat {
    public List<Mensaje> getLast10Messages();

    public Mensaje save(Mensaje m);
}
