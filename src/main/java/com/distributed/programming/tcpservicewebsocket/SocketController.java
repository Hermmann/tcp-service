package com.distributed.programming.tcpservicewebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController{
 
    @MessageMapping("/mensaje")
    @SendTo("/respuesta")
    public String handleMessage(String message) {
        // Aquí va la lógica para procesar el mensaje recibido	
        return "Respuesta: " + message;
    }
}

