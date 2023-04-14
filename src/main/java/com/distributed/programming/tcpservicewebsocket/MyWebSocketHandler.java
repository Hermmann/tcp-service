package com.distributed.programming.tcpservicewebsocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
 
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Aquí va la lógica para procesar el mensaje recibido
        session.sendMessage(new TextMessage("Respuesta: " + payload));
    }
}
