package nixroyal.chat.models.documents;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public @Document class Mensaje implements Serializable {

    private @Id String id;
    private String texto;
    private Long fecha;
    private String username;
    private String tipo;
    private String color;

    private static final long serialVersionUID = 1L;
}
