package org.kevin.web;

import org.jetbrains.annotations.NotNull;
import org.kevin.domain.MCUHistoryInfo;
import org.kevin.domain.reqres.web.HistoryRequest;
import org.kevin.service.MCUHistoryService;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController extends ControllerBase {

    MCUHistoryService mMCUHistoryService;

    @Autowired
    public void setMCUHistoryService(MCUHistoryService MCUHistoryService) {
        mMCUHistoryService = MCUHistoryService;
    }

    @RequestMapping(path = "mcuHistory", method = RequestMethod.POST)
    List<MCUHistoryInfo> mcuHistory(@NotNull @RequestBody HistoryRequest request)
    {
        List<MCUHistoryInfo> response =  mMCUHistoryService.getHistoryByDeviceId(request.getDeviceId());
        return response;
    }
}
