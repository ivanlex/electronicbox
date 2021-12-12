package org.kevin.service;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.dao.MaintainDao;
import org.kevin.dao.UserDao2;
import org.kevin.domain.MCUBasic;
import org.kevin.domain.MCUOpInfo;
import org.kevin.domain.reqres.web.MCUAddUpdateResponse;
import org.kevin.domain.MCUAlertStatics;
import org.kevin.domain.reqres.web.MCURemoveResponse;
import org.kevin.domain.reqres.web.MCUStaticsResponse;
import org.kevin.service.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MaintainService extends ServiceBase {
    UserDao2 mUserDao2;
    MaintainDao mMaintainDao;
    MCUOpInfoDao mMCUOpInfoDao;

    public boolean isExist(String mcuId){
        return mMaintainDao.isMcuExist(mcuId);
    }

    @Autowired
    public void setMaintainDao(MaintainDao maintainDao) {
        mMaintainDao = maintainDao;
    }

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }

    @Autowired
    public void setUserDao2(UserDao2 userDao2) {
        mUserDao2 = userDao2;
    }

    public List<MCUBasic> getAllMCU(String userId){
        List<MCUBasic> mcus =  mMaintainDao.getAllMcu(userId);
        return mcus;
    }

    public MCUAddUpdateResponse createAddMcu(String mcuId, String mcuDesc, String mcuGroup, String installedAddress, double longitude, double latitude, String userId){
        MCUAddUpdateResponse response = new MCUAddUpdateResponse();

        if(isExist(mcuId)){
            response.setDuiplicateMCU(true);
            response.setActionComplete(false);
            return response;
        }
        else{
            mMaintainDao.addNewMcu(mcuId,mcuDesc,mcuGroup,installedAddress,longitude,latitude,new Date(),userId);
            mMCUOpInfoDao.addMCUOpInfo(mcuId);

            response.setDuiplicateMCU(false);
            response.setActionComplete(true);
        }

        return response;
    }

    public MCUAddUpdateResponse updateMcu(String mcuId, String mcuDesc, String mcuGroup, String installedAddress, double longitude, double latitude, String userId){
        MCUAddUpdateResponse response = new MCUAddUpdateResponse();
        mMaintainDao.updateMcu(mcuId,mcuDesc,mcuGroup,installedAddress,longitude,latitude,userId);

        response.setDuiplicateMCU(false);
        response.setActionComplete(true);

        return response;
    }

    public MCURemoveResponse removeMcu(String mcuId,String userId){
        MCURemoveResponse response = new MCURemoveResponse();

        mMaintainDao.removeMcu(mcuId,userId);
        mMCUOpInfoDao.removeMCUOpInfo(mcuId);
        response.setActionComplete(true);

        return response;
    }

    public MCUStaticsResponse getMCUStatics(String userToken){
        MCUStaticsResponse response = new MCUStaticsResponse();

        String userId = mUserDao2.getUserIdByToken(userToken);

        if(userId != null)
        {
            response.setInstalledMCU(mMaintainDao.countInstalledMCU(userId));
            response.setOnlineMCU(mMaintainDao.countOnlineMCU(2,userId));
        }

        return response;
    }

    public List<MCUAlertStatics> getMCUAlertStatics(String userToken, String location, Date date){
        List<MCUAlertStatics> response = new ArrayList<MCUAlertStatics>();

        String userId = mUserDao2.getUserIdByToken(userToken);

        if(userId != null)
        {
           return mMaintainDao.countAlertStatics(userId,location,date);
        }
        else
        {
            // TODO no alert statics
            return null;
        }
    }

    @Deprecated
    public List<MCUOpInfo> getMCUTopStatics(String userId)
    {
        List<MCUOpInfo> mcuOpInfos = mMCUOpInfoDao.getTop5MCUStatus(2,userId);

        while(mcuOpInfos.size() < 5)
        {
            mcuOpInfos.add(new MCUOpInfo());
        }

        return mcuOpInfos;
    }
}
