package org.kevin.domain.reqres.web;

public class HistoryRequest{

    String mDeviceId;
    String mToken;

    public String getDeviceId() {
        return mDeviceId;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public void setDeviceId(String deviceId) {
        mDeviceId = deviceId;
    }

    public HistoryRequest() {
    }
}
