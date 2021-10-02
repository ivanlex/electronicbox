package org.kevin.domain.reqres.web;

public class LoginRequest {
    String mUsername;
    String mPassword;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public LoginRequest(String username, String  password)
    {
        mUsername = username;
        mPassword = password;
    }
}
