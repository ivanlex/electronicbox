package org.kevin.web;

import org.kevin.common.Commons;
import org.kevin.domain.MCUBasic;
import org.kevin.domain.MCUOpInfo;
import org.kevin.domain.reqres.web.*;
import org.kevin.service.MaintainService;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaintainController extends ControllerBase {

    MaintainService mMaintainService;

    @Autowired
    public void setMaintainService(MaintainService maintainService) {
        mMaintainService = maintainService;
    }

    @RequestMapping(path = "mcuAdd/{userId}/",method = RequestMethod.POST)
    public MCUAddUpdateResponse addDevice(@RequestBody MCUAddUpdateRequest request, @PathVariable("userId") String userId){
        String deviceId = request.getDeviceId();
        String desc = request.getDesc() == null ? Commons.EMPTY_STRING : request.getDesc();
        String group = request.getGroup() == null ? Commons.EMPTY_STRING : request.getGroup();
        String deviceAddress = request.getDeviceAddress();
        double longitude = request.getLongitude();
        double latitude = request.getLatitude();


        mCommonUtility.getLogger().info("API:{},deviceId:{},desc:{},group:{},deviceAddress:{},longitude:{},Latitude:{}",
                "mcuAdd/" + userId, deviceId, desc, group, deviceAddress, longitude,latitude);

        return mMaintainService.createAddMcu(
                deviceId,
                desc,
                group,
                deviceAddress,
                longitude,
                latitude,
                userId);
    }

    @RequestMapping(path = "mcuUpdate/{userId}/",method = RequestMethod.POST)
    public MCUAddUpdateResponse updateDevice(@RequestBody MCUAddUpdateRequest request,@PathVariable("userId") String userId){
        String deviceId = request.getDeviceId();
        String desc = request.getDesc() == null ? Commons.EMPTY_STRING : request.getDesc();
        String group = request.getGroup() == null ? Commons.EMPTY_STRING : request.getGroup();
        String deviceAddress = request.getDeviceAddress();
        double longitude = request.getLongitude();
        double latitude = request.getLatitude();

        mCommonUtility.getLogger().info("API:{},deviceId:{},desc:{},group:{},deviceAddress:{},longitude:{},Latitude:{}",
                "mcuUpdate/" + userId, deviceId, desc, group, deviceAddress, longitude,latitude);

        return mMaintainService.updateMcu(
                deviceId,
                desc,
                group,
                deviceAddress,
                longitude,
                latitude,
                userId);
    }

    @RequestMapping(path = "mcuRemove/{userId}", method = RequestMethod.POST)
    public MCURemoveResponse removeDevice(@RequestBody MCURemoveRequest request,@PathVariable("userId") String userId){
        String deviceId = request.getDeviceId();

        mCommonUtility.getLogger().info("API:{},deviceId:{}",
                "mcuRemove/" + userId, deviceId);

        return mMaintainService.removeMcu(request.getDeviceId(),userId);
    }

    @RequestMapping(path = "getAllMcu/{userId}",method = RequestMethod.POST)
    public List<MCUBasic> getAllMCU(@PathVariable("userId") String userId){

        mCommonUtility.getLogger().info("API:{}",
                "getAllMcu/" + userId);

        return mMaintainService.getAllMCU(userId);
    }

    @RequestMapping(path = "mcuStatics/{userId}", method = RequestMethod.POST)
    public MCUStaticsResponse getMCUStatics(@PathVariable String userId){
        mCommonUtility.getLogger().info("API:{}",
                "mcuStatics/" + userId);

        return mMaintainService.getMCUStatics(userId);
    }

    @RequestMapping(path = "mcuTopStatics/{userId}", method = RequestMethod.POST)
    public List<MCUOpInfo> getTopMCU(@PathVariable("userId") String userId)
    {
        mCommonUtility.getLogger().info("API:{}",
                "mcuTopStatics/" + userId);

        return mMaintainService.getMCUTopStatics(userId);
    }
}
