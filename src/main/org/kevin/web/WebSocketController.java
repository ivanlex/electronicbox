package org.kevin.web;

import org.kevin.domain.WebSocketSessionRegistry;
import org.kevin.web.base.ControllerBase;
import org.kevin.websocket.HeartMessage;
import org.kevin.websocket.HeartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController extends ControllerBase {
    WebSocketSessionRegistry mWebSocketSessionRegistry;

    @Autowired
    public void setWebSocketSessionRegistry(WebSocketSessionRegistry webSocketSessionRegistry) {
        mWebSocketSessionRegistry = webSocketSessionRegistry;
    }

    @MessageMapping("/heartbeat")
    @SendTo("/topic/heartbeat")
    public HeartResponse heart(
            @Payload HeartMessage message,
            SimpMessageHeaderAccessor headerAccessor) throws Exception
    {
        Thread.sleep(1000);

        //mCommonUtility.getLogger().error(message.getClientId());

        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        String clientId = message.getClientId();

        mWebSocketSessionRegistry.registerSessionId(clientId ,sessionId);

        return new HeartResponse(message.getClientId());
    }

}
