package org.kevin.utility;

import org.apache.logging.log4j.core.util.KeyValuePair;
import org.kevin.common.Commons;
import org.kevin.service.MCUResponse;
import org.kevin.service.enums.RequestType;
import org.kevin.service.interfaces.IResponse;

import java.util.HashMap;
import java.util.Map;

import static org.kevin.common.Commons.*;

public class MyFactory {
    public static <TResponse extends IResponse> TResponse createSocketResponse(RequestType requestType, HashMap<String, String> properties) {
        StringBuilder sb = new StringBuilder();

        sb.append(requestType.toString());

        for (Map.Entry<String, String> property : properties.entrySet()) {
            sb.append(PAYLOAD_SPLIT);
            sb.append(property.getKey());
            sb.append(PROPERTY_SPLIT);
            sb.append(property.getValue());
        }

        sb.append(PAYLOAD_END);

        return (TResponse) new MCUResponse(sb.toString());
    }
}
