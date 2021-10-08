package org.kevin.web;

import org.kevin.domain.reqres.mcu.LoginPayload;
import org.kevin.service.LoginService;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController extends ControllerBase {

    LoginService mLoginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        mLoginService = loginService;
    }

    @Deprecated
    @RequestMapping(path = "userLogin", method = RequestMethod.POST)
    LoginPayload getUserToken(@RequestParam String username) {

        LoginPayload loginPayload = mLoginService.createUserToken(username);
        if (loginPayload != null)
            return loginPayload;
        else
            return new LoginPayload();
    }

    @Deprecated
    @RequestMapping(path = "userVerify", method = RequestMethod.POST)
    LoginPayload getLoginToken(@RequestParam String userToken, @RequestParam String userPassword) {
        LoginPayload loginPayload = mLoginService.createLoginToken(userToken, userPassword);
        if (loginPayload != null)
            return loginPayload;
        else
            return new LoginPayload();
    }
}
