package org.kevin.domain.reqres.web;

public class LoginResponse {
    String mToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public LoginResponse(String token)
    {
        mToken = token;
    }
}
