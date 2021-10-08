package org.kevin.domain.reqres.web;

public class LoginResponse2 {
    String mToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public LoginResponse2(String token)
    {
        mToken = token;
    }
}
