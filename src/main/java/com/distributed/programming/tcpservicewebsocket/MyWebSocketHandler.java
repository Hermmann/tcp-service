package com.distributed.programming.tcpservicewebsocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> ipAddressToSessionMap = new ConcurrentHashMap<>();


	 // ...

    public void sendMessageToUser(String userId, String message) {
       // WebSocketSession session = sessions.get(userId);
        if (ipAddressToSessionMap != null && ipAddressToSessionMap.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                // Manejar la excepción
            }
        }
    }

    // ...

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
    	
    	// Obtener la dirección IP del destinatario
    	String ipAddress = "192.168.0.100";

    	// Crear el mensaje que se enviará
    	String message = "Hola, esta es una prueba de mensaje TCP push notification.";

    	// Buscar el WebSocketSession correspondiente a la dirección IP del destinatario
    	WebSocketSession recipientSession = ipAddressToSessionMap.get(ipAddress);

    	// Verificar que la sesión existe y esté abierta
    	if (recipientSession != null && recipientSession.isOpen()) {
    	    // Enviar el mensaje al destinatario
    	    recipientSession.sendMessage(new TextMessage(message));
    	} else {
    	    // Manejar el caso en el que la sesión no existe o está cerrada
    	    // ...
    	}

    	
    /**
        // Obtener el identificador único del usuario de la sesión
        String userId = getUserIdFromSession(session);
     
        // Almacenar la sesión en la lista o mapa de sesiones
        sessions.put(userId, session);
        
        
    }

    */
    }
	
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Aquí va la lógica para procesar el mensaje recibido
        session.sendMessage(new TextMessage("Respuesta: " + payload));
    }
}
