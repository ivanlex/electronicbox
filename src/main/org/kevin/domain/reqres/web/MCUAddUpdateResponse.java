package org.kevin.domain.reqres.web;

public class MCUAddUpdateResponse {
    boolean mActionComplete;
    boolean mDuiplicateMCU;

    public boolean isActionComplete() {
        return mActionComplete;
    }

    public void setActionComplete(boolean actionComplete) {
        mActionComplete = actionComplete;
    }

    public boolean isDuiplicateMCU() {
        return mDuiplicateMCU;
    }

    public void setDuiplicateMCU(boolean duiplicateMCU) {
        mDuiplicateMCU = duiplicateMCU;
    }
}
