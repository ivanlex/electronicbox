package org.kevin.web;

import org.kevin.domain.MCUHistoryInfo;
import org.kevin.domain.reqres.web.HistoryRequest;
import org.kevin.service.MCUHistoryService;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistoryController extends ControllerBase {

    MCUHistoryService mMCUHistoryService;

    @Autowired
    public void setMCUHistoryService(MCUHistoryService MCUHistoryService) {
        mMCUHistoryService = MCUHistoryService;
    }

    @RequestMapping(path = "mcuHistory/{owner}", method = RequestMethod.POST)
    List<MCUHistoryInfo> mcuHistory(@RequestBody HistoryRequest request, @PathVariable("owner") String owner)
    {
        mCommonUtility.getLogger().info("API:{}",
                "mcuHistory");

        List<MCUHistoryInfo> response =  mMCUHistoryService.getHistoryByDeviceId(request.getDeviceId());
        return response;
    }
}
