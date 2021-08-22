package org.kevin.domain;

public class MCU {
    Long mId;
    String mMCUId;
    String mMCUPwd;
    String mMCUToken;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getMCUToken() {
        return mMCUToken;
    }

    public void setMCUToken(String MCUToken) {
        mMCUToken = MCUToken;
    }

    public MCU(String MCUId, String MCUPwd, String MCUToken) {
        mMCUId = MCUId;
        mMCUPwd = MCUPwd;
        mMCUToken = MCUToken;
    }

    public String getMCUId() {
        return mMCUId;
    }

    public void setMCUId(String MCUId) {
        mMCUId = MCUId;
    }

    public String getMCUPwd() {
        return mMCUPwd;
    }

    public void setMCUPwd(String MCUPwd) {
        mMCUPwd = MCUPwd;
    }
}
