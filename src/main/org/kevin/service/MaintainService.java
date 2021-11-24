package org.kevin.service;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.dao.MaintainDao;
import org.kevin.domain.MCUBasic;
import org.kevin.domain.MCUOpInfo;
import org.kevin.domain.reqres.web.MCUAddUpdateResponse;
import org.kevin.domain.reqres.web.MCURemoveResponse;
import org.kevin.domain.reqres.web.MCUStaticsResponse;
import org.kevin.service.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaintainService extends ServiceBase {

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

    public List<MCUBasic> getAllMCU(){
        List<MCUBasic> mcus =  mMaintainDao.getAllMcu();
        return mcus;
    }

    public MCUAddUpdateResponse createAddMcu(String mcuId, String mcuDesc, String mcuGroup, String installedAddress, double longitude, double latitude){
        MCUAddUpdateResponse response = new MCUAddUpdateResponse();

        if(isExist(mcuId)){
            response.setDuiplicateMCU(true);
            response.setActionComplete(false);
            return response;
        }
        else{
            mMaintainDao.addNewMcu(mcuId,mcuDesc,mcuGroup,installedAddress,longitude,latitude,new Date());
            mMCUOpInfoDao.addMCUOpInfo(mcuId);

            response.setDuiplicateMCU(false);
            response.setActionComplete(true);
        }

        return response;
    }

    public MCUAddUpdateResponse updateMcu(String mcuId, String mcuDesc, String mcuGroup, String installedAddress, double longitude, double latitude){
        MCUAddUpdateResponse response = new MCUAddUpdateResponse();
        mMaintainDao.updateMcu(mcuId,mcuDesc,mcuGroup,installedAddress,longitude,latitude);

        response.setDuiplicateMCU(false);
        response.setActionComplete(true);

        return response;
    }

    public MCURemoveResponse removeMcu(String mcuId){
        MCURemoveResponse response = new MCURemoveResponse();

        mMaintainDao.removeMcu(mcuId);
        mMCUOpInfoDao.removeMCUOpInfo(mcuId);
        response.setActionComplete(true);

        return response;
    }

    public MCUStaticsResponse getMCUStatics(){
        MCUStaticsResponse response = new MCUStaticsResponse();

        response.setInstalledMCU(mMaintainDao.countInstalledMCU());
        response.setOnlineMCU(mMaintainDao.countOnlineMCU(2));

        return response;
    }

    public List<MCUOpInfo> getMCUTopStatics()
    {
        List<MCUOpInfo> mcuOpInfos = mMCUOpInfoDao.getTop5MCUStatus(2);

        while(mcuOpInfos.size() < 5)
        {
            mcuOpInfos.add(new MCUOpInfo());
        }

        return mcuOpInfos;
    }
}
