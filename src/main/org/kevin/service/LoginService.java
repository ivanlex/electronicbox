package org.kevin.service;

import org.kevin.common.Commons;
import org.kevin.domain.LoginPayLoadFactory;
import org.kevin.domain.LoginPayload;
import org.kevin.domain.MCUInfo;
import org.kevin.domain.SocketFactory;
import org.kevin.service.interfaces.LoginServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService implements LoginServiceInterface {


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCUExist(MCUInfo mcuInfo) {
        return true;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCUValid(MCUInfo mcuInfo) {
        return true;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCUTokenValid(MCUInfo mcuInfo) {
        return true;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean isMCURequireReboot(MCUInfo mcuInfo) {
        return true;
    }


    @Transactional(isolation = Isolation.DEFAULT)
    public String createMCUToken(MCUInfo mcuInfo) {
        return Commons.EMPTY_STRING;
    }

    public boolean isUserExist(String username) {
        return false;
    }

    public boolean isUserLocked(String username) {
        return false;
    }

    public boolean isUserTokenExist(String userToken, String userPwd) {
        return false;
    }

    public boolean isUserTokenLocked(String userToken, String userPwd) {
        return false;
    }

    public String generateUserToken(String username) {
        return Commons.EMPTY_STRING;
    }

    public String generateLoginToken(String userToken) {
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
