package org.kevin.service;

import org.kevin.dao.MCUHistoryDao;
import org.kevin.dao.UserDao2;
import org.kevin.domain.MCUHistoryInfo;
import org.kevin.service.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCUHistoryService extends ServiceBase {

    MCUHistoryDao mMCUHistoryDao;
    UserDao2 mUserDao2;

    @Autowired
    public void setUserDao2(UserDao2 userDao2) {
        mUserDao2 = userDao2;
    }


    @Autowired
    public void setMCUHistoryDao(MCUHistoryDao MCUHistoryDao) {
        mMCUHistoryDao = MCUHistoryDao;
    }

    public List<MCUHistoryInfo> getHistoryByDeviceId(String deviceId,String token)
    {
        String userId = mUserDao2.getUserIdByToken(token);

       return mMCUHistoryDao.getAllMCUHistoryInfo(deviceId, userId);
    }


}
