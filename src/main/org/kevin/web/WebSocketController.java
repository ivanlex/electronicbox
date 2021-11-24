package org.kevin.web;

import org.kevin.web.base.ControllerBase;
import org.kevin.websocket.HeartMessage;
import org.kevin.websocket.HeartResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController extends ControllerBase {

    @MessageMapping("/heartbeat")
    @SendTo("/topic/heartbeat")
    public HeartResponse heart(HeartMessage message) throws Exception
    {
        Thread.sleep(5000);

        mCommonUtility.getLogger().error(message.getClientId());

        return new HeartResponse(message.getClientId());
    }

}
