package nixroyal.chat.models.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import nixroyal.chat.models.documents.Mensaje;

public interface RChat extends MongoRepository<Mensaje, String> {

    public List<Mensaje> findFirst10ByOrderByFechaDesc();
    
}
