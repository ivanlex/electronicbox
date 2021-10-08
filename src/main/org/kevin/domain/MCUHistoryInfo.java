package org.kevin.domain;

import java.util.Date;

public class MCUHistoryInfo {
    Long mId;
    String mMcuId;
    int mOpenStatus;
    int mCrackStatus;
    int mLightningStatus;
    int mGroundedStatus;
    int mLightningCount;
    Date mUpdatedTime;

    public Date getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        mUpdatedTime = updatedTime;
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
