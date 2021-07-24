package org.kevin.service;

import org.kevin.common.Commons;
import org.kevin.domain.MCUInfo;
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


}
