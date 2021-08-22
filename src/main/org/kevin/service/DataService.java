package org.kevin.service;

import org.kevin.common.Commons;
import org.kevin.dao.MCUDao;
import org.kevin.domain.MCU;
import org.kevin.service.interfaces.DataServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService implements DataServiceInterface {

    LoginService mLoginService;
    MCUDao mMCUDao;

    @Autowired
    private void setLoginService(LoginService loginService) {
        mLoginService = loginService;
    }

    @Autowired
    private void setMCUDao(MCUDao mcuDao) {
        mMCUDao = mcuDao;
    }

    public List<MCU> getMCUs(String loginToken) {
        if (loginToken == null || loginToken == Commons.EMPTY_STRING)
            return null;

        if (!mLoginService.isLoginTokenValid(loginToken))
            return null;
        else {
            List<MCU> mcus = mMCUDao.getMCUByLoginToken(loginToken);

            return mcus;
        }
    }
}
