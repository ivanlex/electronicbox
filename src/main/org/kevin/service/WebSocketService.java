package org.kevin.service;

import org.kevin.dao.MaintainDao;
import org.kevin.domain.MCUBasic;
import org.kevin.domain.WSNotifyType;
import org.kevin.domain.WebSocketSessionRegistry;
import org.kevin.service.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService extends ServiceBase {
    SimpMessagingTemplate mSimpMessagingTemplate;
    MaintainDao mMaintainDao;
    WebSocketSessionRegistry mWebSocketSessionRegistry;

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        mSimpMessagingTemplate = simpMessagingTemplate;
    }

    @Autowired
    public void setMaintainDao(MaintainDao maintainDao) {
        mMaintainDao = maintainDao;
    }

    @Autowired
    public void setWebSocketSessionRegistry(WebSocketSessionRegistry webSocketSessionRegistry) {
        mWebSocketSessionRegistry = webSocketSessionRegistry;
    }

    public void notifyUserInfo(String mcuId, WSNotifyType wsNotifyType)
    {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        MCUBasic mcu =  mMaintainDao.getMcuById(mcuId);

        if (mcu == null && !mcu.getUserId().isEmpty()){
            return;
        }
        String sessionId =  mWebSocketSessionRegistry.getSessionId(mcu.getUserId());

        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);

        mSimpMessagingTemplate.convertAndSendToUser(
                sessionId,
                "/topic/heartbeat",
                wsNotifyType.toString(),
                headerAccessor.getMessageHeaders());
    }
}
