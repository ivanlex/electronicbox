package org.kevin.service;

import org.kevin.domain.MCUInfo;
import org.kevin.service.enums.RequestType;
import org.kevin.service.interfaces.IRequest;
import org.kevin.service.interfaces.IResponse;
import org.kevin.domain.SocketFactory;
import org.kevin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static org.kevin.common.Commons.*;

@Service
public class MCUSessionMgr {
    LoginService mLoginService;
    DataUploadService mDataUploadService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        mLoginService = loginService;
    }

    @Autowired
    public void setDataUploadService(DataUploadService dataUploadService) {
        mDataUploadService = dataUploadService;
    }


    public IResponse ProcessRequest(IRequest request) {
        HashMap<String, String> resProperties = new HashMap<>();
        HashMap<String, String> reqProperties = request.getProperties();
        RequestType requestType = request.getRequestType();

        if (!Utility.validateMCUMsg(request)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_FORMAT_401);
        } else {
            MCUInfo mcuInfo = Utility.extractUserInfo(reqProperties, requestType != RequestType.LU);

            if (mcuInfo != null) {
                switch (requestType) {
                    case LU:
                        ProcessMCULogin(mcuInfo, resProperties);
                        break;
                    case UD:
                        ProcessMCUDataUpload(mcuInfo, reqProperties, resProperties);
                        break;
                    case UNDEFINED:
                        resProperties.put(KEY_ERR_CODE, ERR_SOCKET_UNKNOWN_REQUEST_TYPE_402);
                        break;
                }
            } else
                resProperties.put(KEY_ERR_CODE, ERR_SOCKET_KEYS_INCOMPLETE_403);
        }

        return SocketFactory.createSocketResponse(requestType, resProperties);
    }

    public void ProcessMCULogin(MCUInfo mcuInfo, HashMap<String, String> resProperties) {
        if (!mLoginService.isMCUExist(mcuInfo)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_MCU_NOT_EXIST_404);
            return;
        }

        if (!mLoginService.isMCUValid(mcuInfo)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_MCU_NOT_VALID_405);
            return;
        }

        resProperties.put(KEY_MCU_TOKEN, mLoginService.createMCUToken(mcuInfo));
    }

    public void ProcessMCUDataUpload(MCUInfo mcuInfo, HashMap<String, String> reqProperties, HashMap<String, String> resProperties) {
        if (!mLoginService.isMCUTokenValid(mcuInfo)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_MCU_TOKEN_NOT_VALID_406);
            return;
        }

        mDataUploadService.uploadDatas(mcuInfo, reqProperties);

        if (!mLoginService.isMCURequireReboot(mcuInfo))
            resProperties.put(KEY_MCU_REBOOT, VALUE_MCU_DONT_REBOOT);
        else
            resProperties.put(KEY_MCU_REBOOT, VALUE_MCU_NEED_REBOOT);
    }


}
