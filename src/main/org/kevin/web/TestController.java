package org.kevin.web;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.domain.MCU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    MCUOpInfoDao mMCUOpInfoDao;

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }

    @RequestMapping(path = "mcuupdate", method = RequestMethod.POST)
    void getMCUs() {
        mMCUOpInfoDao.updateMCUOpInfo("1",0,0,0,0,26);
    }
}
