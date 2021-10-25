package org.kevin.web;

import org.kevin.common.Commons;
import org.kevin.domain.MCUBasic;
import org.kevin.domain.MCUOpInfo;
import org.kevin.domain.reqres.web.*;
import org.kevin.service.MaintainService;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaintainController extends ControllerBase {

    MaintainService mMaintainService;

    @Autowired
    public void setMaintainService(MaintainService maintainService) {
        mMaintainService = maintainService;
    }

    @RequestMapping(path = "mcuAdd",method = RequestMethod.POST)
    public MCUAddResponse addDevice(@RequestBody MCUAddRequest request){
        return mMaintainService.createAddMcu(
                request.getDeviceId(),
                request.getDesc() == null ? Commons.EMPTY_STRING : request.getDesc(),
                request.getGroup() == null ? Commons.EMPTY_STRING : request.getGroup(),
                request.getDeviceAddress(),
                request.getLongitude(),
                request.getLatitude());
    }

    @RequestMapping(path = "mcuRemove", method = RequestMethod.POST)
    public MCURemoveResponse removeDevice(@RequestBody MCURemoveRequest request){
        return mMaintainService.removeMcu(request.getDeviceId());
    }

    @RequestMapping(path = "getAllMcu",method = RequestMethod.POST)
    public List<MCUBasic> getAllMCU(){
        return mMaintainService.getAllMCU();
    }

    @RequestMapping(path = "mcuStatics", method = RequestMethod.POST)
    public MCUStaticsResponse getMCUStatics(){
        return mMaintainService.getMCUStatics();
    }

    @RequestMapping(path = "mcuTopStatics", method = RequestMethod.POST)
    public List<MCUOpInfo> getTopMCU()
    {
        return mMaintainService.getMCUTopStatics();
    }
}
