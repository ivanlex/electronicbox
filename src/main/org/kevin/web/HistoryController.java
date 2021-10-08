package org.kevin.web;

import org.jetbrains.annotations.NotNull;
import org.kevin.domain.MCUHistoryInfo;
import org.kevin.domain.reqres.web.HistoryRequest;
import org.kevin.domain.reqres.web.LoginRequest2;
import org.kevin.domain.reqres.web.LoginResponse2;
import org.kevin.service.MCUHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    MCUHistoryService mMCUHistoryService;

    @Autowired
    public void setMCUHistoryService(MCUHistoryService MCUHistoryService) {
        mMCUHistoryService = MCUHistoryService;
    }

    @RequestMapping(path = "mcuHistory", method = RequestMethod.POST)
    List<MCUHistoryInfo> mcuHistory(@NotNull @RequestBody HistoryRequest request)
    {
        //return new LoginResponse2("123");
        List<MCUHistoryInfo> response =  mMCUHistoryService.getHistoryByDeviceId(request.getDeviceId());
        return response;
    }
}
