package org.kevin.service.interfaces;

import org.kevin.service.enums.RequestType;

import java.util.HashMap;

public interface IRequest<T extends IResponse> {
    T getResponse();

    HashMap<String, String> getProperties();

    RequestType getRequestType();

    String getMsg();
}
