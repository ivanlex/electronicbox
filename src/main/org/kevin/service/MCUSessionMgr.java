package org.kevin.service;

import org.kevin.domain.MCU;
import org.kevin.service.enums.RequestType;
import org.kevin.service.interfaces.IRequest;
import org.kevin.service.interfaces.IResponse;
import org.kevin.domain.SocketFactory;
import org.kevin.utility.CommonUtility;
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

        if (!CommonUtility.validateMCUMsg(request)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_FORMAT_401);
        } else {
            MCU mcu = CommonUtility.extractUserInfo(reqProperties, requestType != RequestType.LU);

            if (mcu != null) {
                switch (requestType) {
                    case LU:
                        ProcessMCULogin(mcu, resProperties);
                        break;
                    case UD:
                        ProcessMCUDataUpload(mcu, reqProperties, resProperties);
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

    public void ProcessMCULogin(MCU mcu, HashMap<String, String> resProperties) {
        if (!mLoginService.isMCUExist(mcu)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_MCU_NOT_EXIST_404);
            return;
        }

        if (!mLoginService.isMCUValid(mcu)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_MCU_NOT_VALID_405);
            return;
        }

        resProperties.put(KEY_MCU_TOKEN, mLoginService.createMCUToken(mcu));
    }

    public void ProcessMCUDataUpload(MCU mcu, HashMap<String, String> reqProperties, HashMap<String, String> resProperties) {
        if (!mLoginService.isMCUTokenValid(mcu)) {
            resProperties.put(KEY_ERR_CODE, ERR_SOCKET_MCU_TOKEN_NOT_VALID_406);
            return;
        }

        mDataUploadService.uploadDatas(mcu, reqProperties);

        if (!mLoginService.isMCURequireReboot(mcu))
            resProperties.put(KEY_MCU_REBOOT, VALUE_MCU_DONT_REBOOT);
        else
            resProperties.put(KEY_MCU_REBOOT, VALUE_MCU_NEED_REBOOT);
    }


}
