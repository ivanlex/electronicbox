package org.kevin.service;

import org.kevin.dao.MCUHistoryDao;
import org.kevin.domain.MCUHistoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCUHistoryService {

    MCUHistoryDao mMCUHistoryDao;

    @Autowired
    public void setMCUHistoryDao(MCUHistoryDao MCUHistoryDao) {
        mMCUHistoryDao = MCUHistoryDao;
    }

    public List<MCUHistoryInfo> getHistoryByDeviceId(String deviceId)
    {
       return mMCUHistoryDao.getAllMCUHistoryInfo(deviceId);
    }


}