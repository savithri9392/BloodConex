package com.example.demo.controller;

import com.example.demo.models.RequestStatusUpdate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller  // ✅ Marks this as a Spring Controller
public class WebSocketController {

    @MessageMapping("/updateRequestStatus")  // ✅ Maps WebSocket messages to this method
    @SendTo("/topic/requests")  // ✅ Broadcasts updates to subscribers
    public RequestStatusUpdate updateRequestStatus(@Payload RequestStatusUpdate request) {
        System.out.println("📩 Received status update: " + request);
        return request; // Sends the updated request status to all connected clients
    }
}
