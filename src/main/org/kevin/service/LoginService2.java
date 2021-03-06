package org.kevin.service;

import org.kevin.common.Commons;
import org.kevin.dao.UserDao2;
import org.kevin.domain.User;
import org.kevin.domain.reqres.web.LoginResponse;
import org.kevin.service.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.kevin.common.Commons.TEST_TOKEN;

@Service
public class LoginService2 extends ServiceBase {
    UserDao2 mUserDao;

    @Autowired
    public void setUserDao(UserDao2 userDao) {
        mUserDao = userDao;
    }

    public LoginResponse createLoginToken(String username, String password) {
        User user = findUserByPwd(username,password);
        if(user != null)
        {
            genLoginToken(user);
            return new LoginResponse(user.getLoginToken());
        }
        return new LoginResponse(Commons.EMPTY_STRING);
    }

    public void updateWSSessionId(String token, String sessionId)
    {
        if (mUserDao.validateToken(token))
        {
            mUserDao.updateWSSessionId(token, sessionId, new Date());
        }
    }

    private User findUserByPwd(String username,String password){
        User user = mUserDao.getUserByPWD(username,password);
        return user;
    }

    private void genLoginToken(User user){
        user.setLoginToken(TEST_TOKEN);
        mUserDao.updateToken(user,TEST_TOKEN);
    }





}
