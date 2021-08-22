package org.kevin.utility;

import org.kevin.domain.MCU;
import org.kevin.service.interfaces.IRequest;
import org.kevin.system.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.kevin.common.Commons.*;

@Component
public class CommonUtility {

    SystemConfig mSystemConfig;

    @Autowired
    protected void setSystemConfig(SystemConfig systemConfig) {
        mSystemConfig = systemConfig;
    }

    public String getSystemVersion() {
        return String.format("System version is %s", mSystemConfig.version);
    }

    public static MCU extractUserInfo(HashMap<String, String> properties, boolean checkToken) {
        String mcuId = null;
        String mcuPwd = null;
        String mcuToken = null;
        if (properties.containsKey(KEY_MCU_ID))
            mcuId = properties.get(KEY_MCU_ID);

        if (properties.containsKey(KEY_MCU_PWD))
            mcuPwd = properties.get(KEY_MCU_PWD);

        if (properties.containsKey(KEY_MCU_TOKEN))
            mcuToken = properties.get(KEY_MCU_TOKEN);

        if (mcuId == null ||
                mcuPwd == null ||
                (checkToken ? mcuToken == null : true))
            return null;
        else
            return new MCU(mcuId, mcuPwd, mcuToken);
    }

    public static boolean validateMCUMsg(IRequest sb) {
        String msg = sb.getMsg();

        String[] s1 = msg.split(PAYLOAD_SPLIT);

        boolean requestTypeCheck = false;
        boolean endSymbolCheck = false;
        boolean dataIntegrityCheck = true;

        for (int i = 0; i < RequestTypeEnums.length; i++)
            if (s1[0] == RequestTypeEnums[i]) {
                requestTypeCheck = true;
                break;
            }

        if (msg.endsWith(PAYLOAD_END))
            endSymbolCheck = true;

        for (int i = 1; i < RequestTypeEnums.length - 1; i++) {
            String[] s = s1[i].split(PROPERTY_SPLIT);
            if (s.length != 2)
                dataIntegrityCheck = false;
        }


        return requestTypeCheck && endSymbolCheck && dataIntegrityCheck;
    }
}
