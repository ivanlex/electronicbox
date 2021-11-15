package org.kevin.web;

import org.kevin.websocket.HeartMessage;
import org.kevin.websocket.HeartResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/heart")
    @SendTo("/topic/heart")
    public HeartResponse heart(HeartMessage message) throws Exception
    {
        Thread.sleep(1000);
        return new HeartResponse(message.getClientId());
    }

}
