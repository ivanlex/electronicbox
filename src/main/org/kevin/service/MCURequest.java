package org.kevin.service;

import org.kevin.Application;
import org.kevin.service.enums.RequestType;
import org.kevin.service.interfaces.IRequest;
import org.kevin.service.interfaces.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;


public class MCURequest<T extends IResponse> implements IRequest {

    StringBuilder mMsg;

    public MCURequest(StringBuilder msg) {
        mMsg = msg;
    }


    @Override
    public T getResponse() {
        //TODO getResponse
        return null;
    }

    @Override
    public HashMap<String, String> getProperties() {
        //TODO getProperties
        return null;
    }

    @Override
    public RequestType getRequestType() {
        //TODO getRequestType
        return null;
    }

    @Override
    public String getMsg() {
        //TODO getMsg
        return null;
    }
}
