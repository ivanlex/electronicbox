package org.kevin.web;

import org.kevin.websocket.HeartMessage;
import org.kevin.websocket.HeartResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    @MessageMapping("/heartbeat")
    @SendTo("/topic/heartbeat")
    public HeartResponse heart(Principal principal, HeartMessage message) throws Exception
    {
        Thread.sleep(5000);
        System.out.println(principal.getName());
        return new HeartResponse(message.getToken());
    }

}
