package org.kevin.service;

import org.kevin.service.interfaces.IResponse;

public class MCUResponse implements IResponse {
    String mMsg;

    public MCUResponse(String msg) {
        mMsg = msg;
    }


    @Override
    public String getResponse() {
        return mMsg;
    }
}
