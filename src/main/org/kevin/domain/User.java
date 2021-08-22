package org.kevin.domain;

public class User {
    Long mId;
    String mUserId;
    String mUserName;
    String mUserPwd;
    String mUserToken;
    String mLoginToken;
    boolean mUserLocked;

    public String getLoginToken() {
        return mLoginToken;
    }

    public void setLoginToken(String loginToken) {
        mLoginToken = loginToken;
    }

    public boolean isUserLocked() {
        return mUserLocked;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserPwd() {
        return mUserPwd;
    }

    public void setUserPwd(String userPwd) {
        mUserPwd = userPwd;
    }

    public String getUserToken() {
        return mUserToken;
    }

    public void setUserToken(String userToken) {
        mUserToken = userToken;
    }

    public boolean getUserLocked() {
        return mUserLocked;
    }

    public void setUserLocked(boolean userLocked) {
        mUserLocked = userLocked;
    }
}
