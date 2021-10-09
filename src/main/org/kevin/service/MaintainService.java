package org.kevin.service;

import org.kevin.dao.MaintainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintainService {

    MaintainDao mMaintainDao;

    @Autowired
    public void setMaintainDao(MaintainDao maintainDao) {
        mMaintainDao = maintainDao;
    }
}
