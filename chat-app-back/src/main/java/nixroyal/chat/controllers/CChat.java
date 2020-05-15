package nixroyal.chat.controllers;

import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import nixroyal.chat.models.documents.Mensaje;
import nixroyal.chat.models.services.SChat;

@Controller
public class CChat {

    private @Autowired SChat sv;
    private @Autowired SimpMessagingTemplate websocket;

    private String[] colors = { "badge-danger", "badge-dark", "badge-info", "badge-primary",
            "badge-success", "badge-secondary", "badge-warning" };

    @MessageMapping("/msj")
    @SendTo("/chat/msj")
    public Mensaje recibeMensaje(Mensaje m) {
        m.setFecha(new Date().getTime());
        if (m.getTipo().equals("NEW_USER")) {
            m.setColor(colors[new Random().nextInt(colors.length)]);
            m.setTexto("se conectó");
        } else {
            sv.save(m);
        }
        return m;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username) {
        return username.concat(" está escribiendo xd...");
    }

    @MessageMapping("/history")
    public void getHistory(String clienteId) {
        websocket.convertAndSend("/chat/history/" + clienteId, sv.getLast10Messages());
    }

}
