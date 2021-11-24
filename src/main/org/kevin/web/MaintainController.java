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
    public MCUAddUpdateResponse addDevice(@RequestBody MCUAddUpdateRequest request){
        String deviceId = request.getDeviceId();
        String desc = request.getDesc() == null ? Commons.EMPTY_STRING : request.getDesc();
        String group = request.getGroup() == null ? Commons.EMPTY_STRING : request.getGroup();
        String deviceAddress = request.getDeviceAddress();
        double longitude = request.getLongitude();
        double latitude = request.getLatitude();


        mCommonUtility.getLogger().info("API:{},deviceId:{},desc:{},group:{},deviceAddress:{},longitude:{},Latitude:{}",
                "mcuAdd", deviceId, desc, group, deviceAddress, longitude,latitude);

        return mMaintainService.createAddMcu(
                deviceId,
                desc,
                group,
                deviceAddress,
                longitude,
                latitude);
    }

    @RequestMapping(path = "mcuUpdate",method = RequestMethod.POST)
    public MCUAddUpdateResponse updateDevice(@RequestBody MCUAddUpdateRequest request){
        String deviceId = request.getDeviceId();
        String desc = request.getDesc() == null ? Commons.EMPTY_STRING : request.getDesc();
        String group = request.getGroup() == null ? Commons.EMPTY_STRING : request.getGroup();
        String deviceAddress = request.getDeviceAddress();
        double longitude = request.getLongitude();
        double latitude = request.getLatitude();

        mCommonUtility.getLogger().info("API:{},deviceId:{},desc:{},group:{},deviceAddress:{},longitude:{},Latitude:{}",
                "mcuUpdate", deviceId, desc, group, deviceAddress, longitude,latitude);

        return mMaintainService.updateMcu(
                deviceId,
                desc,
                group,
                deviceAddress,
                longitude,
                latitude);
    }

    @RequestMapping(path = "mcuRemove", method = RequestMethod.POST)
    public MCURemoveResponse removeDevice(@RequestBody MCURemoveRequest request){
        String deviceId = request.getDeviceId();

        mCommonUtility.getLogger().info("API:{},deviceId:{}",
                "mcuRemove", deviceId);

        return mMaintainService.removeMcu(request.getDeviceId());
    }

    @RequestMapping(path = "getAllMcu",method = RequestMethod.POST)
    public List<MCUBasic> getAllMCU(){

        mCommonUtility.getLogger().info("API:{}",
                "getAllMcu");

        return mMaintainService.getAllMCU();
    }

    @RequestMapping(path = "mcuStatics", method = RequestMethod.POST)
    public MCUStaticsResponse getMCUStatics(){
        mCommonUtility.getLogger().info("API:{}",
                "mcuStatics");

        return mMaintainService.getMCUStatics();
    }

    @RequestMapping(path = "mcuTopStatics", method = RequestMethod.POST)
    public List<MCUOpInfo> getTopMCU()
    {
        mCommonUtility.getLogger().info("API:{}",
                "mcuTopStatics");

        return mMaintainService.getMCUTopStatics();
    }
}
