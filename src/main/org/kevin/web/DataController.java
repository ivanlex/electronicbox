package org.kevin.web;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.domain.MCUOpInfo;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController extends ControllerBase {

    MCUOpInfoDao mMCUOpInfoDao;

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }

        @RequestMapping(path = "mCUOpInfo/{userId}/", method = RequestMethod.POST)
    List<MCUOpInfo> getMCUOpInfo(@PathVariable("userId") String userId) {
        mCommonUtility.getLogger().info("API:{}",
                    "mCUOpInfo");

        return mMCUOpInfoDao.getAllMCUOpInfo(2, userId);
    }
}
