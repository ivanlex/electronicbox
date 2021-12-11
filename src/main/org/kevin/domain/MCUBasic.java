package org.kevin.domain;

import java.util.Date;

public class MCUBasic {
    Long mId;
    String mMcuId;
    String mAddress;
    String mGroup;
    String mDesc;
    double mLatitude;
    double mLongitude;
    Date mInstallDate;
    String mUserId;

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        mGroup = group;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getMcuId() {
        return mMcuId;
    }

    public void setMcuId(String mcuId) {
        mMcuId = mcuId;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Date getInstallDate() {
        return mInstallDate;
    }

    public void setInstallDate(Date installDate) {
        mInstallDate = installDate;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }
}
