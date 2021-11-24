package org.kevin.web;

import org.kevin.domain.reqres.web.LoginRequest;
import org.kevin.domain.reqres.web.LoginResponse;
import org.kevin.service.LoginService2;
import org.kevin.web.base.ControllerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends ControllerBase {
    LoginService2 mLoginService2;
    SimpMessagingTemplate mSimpMessagingTemplate;

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        mSimpMessagingTemplate = simpMessagingTemplate;
    }

    @Autowired
    public void setLoginService2(LoginService2 loginService2) {
        mLoginService2 = loginService2;
    }

    @RequestMapping(path = "loginVerify", method = RequestMethod.POST)
    LoginResponse loginVerify(@RequestBody LoginRequest request)
    {
        String userName = request.getUsername();
        String password = request.getPassword();
        LoginResponse response =  mLoginService2.createLoginToken(userName,password);

        mCommonUtility.getLogger().info("API:{},username:{},password:{}","loginVerify",userName,password);

        return response;
    }

}
