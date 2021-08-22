package org.kevin.service;

import org.kevin.common.Commons;
import org.kevin.dao.MCUDao;
import org.kevin.dao.UserDao;
import org.kevin.domain.*;
import org.kevin.service.interfaces.LoginServiceInterface;
import org.kevin.utility.MD5Utility;
import org.kevin.utility.RSAUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginService implements LoginServiceInterface {

    UserDao mUserDao;
    MCUDao mMCUDao;

    @Autowired
    private void setUserDao(UserDao userDao) {
        mUserDao = userDao;
    }

    @Autowired
    private void setMCUDao(MCUDao mcuDao) {
        mMCUDao = mcuDao;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCUExist(MCU mcuInfo) {
        if (mcuInfo == null || mcuInfo.getMCUId() == null)
            return false;

        MCU mcu = mMCUDao.getMCUByMCUID(mcuInfo.getMCUId());

        if (mcu != null)
            return true;

        return false;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCUValid(MCU mcu) {
        if (mcu == null || mcu.getMCUId() == null)
            return false;

        return mMCUDao.validateMCU(mcu);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCUTokenValid(MCU mcu) {
        if (mcu == null || mcu.getMCUId() == null)
            return false;

        return mMCUDao.validateMCUToken(mcu);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCURequireReboot(MCU mcu) {
        if (mcu == null || mcu.getMCUId() == null)
            return false;

        return mMCUDao.getMCURebootStatus(mcu);
    }


    @Transactional(isolation = Isolation.DEFAULT)
    public String createMCUToken(MCU mcu) {
        return Commons.EMPTY_STRING;
    }

    public boolean isUserExist(String username) {
        User user = mUserDao.getByUsername(username);

        if (user != null)
            return true;
        return false;
    }

    public boolean isUserLocked(String username) {
        User user = mUserDao.getByUsername(username);

        if (user != null)
            return user.getUserLocked();
        return true;
    }

    public boolean isUserTokenExist(String userToken, String userPwd) {
        User user = mUserDao.validateUserTokenPWD(userToken, userPwd);
        if (user != null)
            return true;
        return false;
    }

    public boolean isUserTokenLocked(String userToken, String userPwd) {
        boolean isLocked = mUserDao.getUserLockstatus(userToken, userPwd);
        return isLocked;
    }

    public boolean isLoginTokenValid(String loginToken) {
        return false;
    }

    public String generateUserToken(String username) {
        User user = mUserDao.getByUsername(username);

        if (user != null) {
            String cipherText = MD5Utility.Encryption(user.getUserName() + new Date());
            user.setUserToken(cipherText);
            mUserDao.update(user);
            return user.getUserToken();
        } else
            return Commons.EMPTY_STRING;
    }

    public String generateLoginToken(String userToken) {
        User user = mUserDao.getByUserToken(userToken);

        if (user != null) {
            String cipherText = MD5Utility.Encryption(user.getUserName() + new Date());
            user.setLoginToken(cipherText);
            mUserDao.update(user);
            return user.getUserToken();
        } else
            return Commons.EMPTY_STRING;
    }

    public LoginPayload createUserToken(String username) {
        return LoginPayLoadFactory.Create(isUserExist(username),
                isUserLocked(username),
                LoginPayLoadFactory.TokenType.UserToken,
                generateUserToken(username));
    }

    public LoginPayload createLoginToken(String userToken, String userPwd) {
        return LoginPayLoadFactory.Create(isUserTokenExist(userToken, userPwd),
                isUserTokenLocked(userToken, userPwd),
                LoginPayLoadFactory.TokenType.LoginToken,
                generateLoginToken(userToken));
    }


}
