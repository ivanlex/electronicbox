package org.kevin.domain.reqres.mcu;

import org.kevin.domain.base.PayloadBase;

public class LoginPayload extends PayloadBase {
    public LoginPayload() {

    }

    public String getCode() {
        return this.mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public String getUserToken() {
        return this.mUserToken;
    }

    public void setUserToken(String userToken) {
        this.mUserToken = userToken;
    }

    public String getLoginToken() {
        return this.mLoginToken;
    }

    public void setLoginToken(String loginToken) {
        this.mLoginToken = loginToken;
    }

}
