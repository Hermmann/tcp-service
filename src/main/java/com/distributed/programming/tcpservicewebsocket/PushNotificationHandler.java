package com.distributed.programming.tcpservicewebsocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class PushNotificationHandler implements WebSocketHandler {
	private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Aquí va la lógica para procesar el mensaje recibido
        // En este caso, simplemente enviamos el mensaje a todos los clientes conectados
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(payload));
        }
    }

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

    /*
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        // Implementación opcional para manejar mensajes binarios
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        // Implementación opcional para manejar mensajes Pong
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Implementación opcional para manejar errores de transporte
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
    */
}
