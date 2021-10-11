package org.kevin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MCUOpInfo {
    Long mId;
    String mMcuId;
    int mOpenStatus;
    int mCrackStatus;
    int mLightningStatus;
    int mGroundedStatus;
    int mLightningCount;
    Date mUpdateTime;
    int mIsOnline;

    public void setIsOnline(int isOnline) {
        mIsOnline = isOnline;
    }

    public Date getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        mUpdateTime = updateTime;
    }

    public int getIsOnline() {
        return mIsOnline;
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

    public int getOpenStatus() {
        return mOpenStatus;
    }

    public void setOpenStatus(int openStatus) {
        mOpenStatus = openStatus;
    }

    public int getCrackStatus() {
        return mCrackStatus;
    }

    public void setCrackStatus(int crackStatus) {
        mCrackStatus = crackStatus;
    }

    public int getLightningStatus() {
        return mLightningStatus;
    }

    public void setLightningStatus(int lightningStatus) {
        mLightningStatus = lightningStatus;
    }

    public int getGroundedStatus() {
        return mGroundedStatus;
    }

    public void setGroundedStatus(int groundedStatus) {
        mGroundedStatus = groundedStatus;
    }

    public int getLightningCount() {
        return mLightningCount;
    }

    public void setLightningCount(int lightningCount) {
        mLightningCount = lightningCount;
    }
}
