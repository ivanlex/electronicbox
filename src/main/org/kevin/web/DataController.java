package org.kevin.web;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.dao.UserDao2;
import org.kevin.domain.MCUOpInfo;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController extends ControllerBase {

    MCUOpInfoDao mMCUOpInfoDao;
    UserDao2 mUserDao2;

    @Autowired
    public void setUserDao2(UserDao2 userDao2) {
        mUserDao2 = userDao2;
    }

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }

        @RequestMapping(path = "mCUOpInfo", method = RequestMethod.POST)
    List<MCUOpInfo> getMCUOpInfo(@RequestParam("token") String token) {
        mCommonUtility.getLogger().info("API:{}",
                    "mCUOpInfo");

            String userId = mUserDao2.getUserIdByToken(token);

        return mMCUOpInfoDao.getAllMCUOpInfo(2, userId);
    }
}
