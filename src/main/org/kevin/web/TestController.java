package org.kevin.web;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController extends ControllerBase {
    MCUOpInfoDao mMCUOpInfoDao;
    SimpMessagingTemplate mSimpMessagingTemplate;

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        mSimpMessagingTemplate = simpMessagingTemplate;
    }

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }

    @RequestMapping(path = "mcuupdate", method = RequestMethod.POST)
    void getMCUs() {
        mMCUOpInfoDao.updateMCUOpInfo("1",0,0,0,0,26,new Date());
    }


    @RequestMapping(path="wsTestMessage", method = RequestMethod.POST)
    void wsMessageSend(@RequestParam String sessionId){
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);

        mSimpMessagingTemplate.convertAndSendToUser(
                sessionId,
                "/topic/heartbeat",
                "test",
                headerAccessor.getMessageHeaders());

        mCommonUtility.getLogger().error("Test API with WebSocket");
    }
}
