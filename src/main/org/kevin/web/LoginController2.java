package org.kevin.web;

import org.kevin.domain.reqres.web.LoginRequest2;
import org.kevin.domain.reqres.web.LoginResponse2;
import org.kevin.service.LoginService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController2 {
    LoginService2 mLoginService2;

    @Autowired
    public void setLoginService2(LoginService2 loginService2) {
        mLoginService2 = loginService2;
    }

    @RequestMapping(path = "loginVerify", method = RequestMethod.POST)
    LoginResponse2 loginVerify(@RequestBody LoginRequest2 request)
    {
        //return new LoginResponse2("123");
        LoginResponse2  response =  mLoginService2.createLoginToken(request.getUsername(),request.getPassword());
        return response;
    }

}
