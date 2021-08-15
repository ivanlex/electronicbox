package org.kevin.domain;

import org.kevin.common.Commons;

public class LoginPayLoadFactory {
    public static LoginPayload Create(boolean isExist, boolean isLocked, TokenType tokenType, String param) {
        LoginPayload payload = new LoginPayload();

        switch (tokenType) {
            case UserToken:
                if (!isExist) {
                    payload.setCode(Commons.ERR_WEB_USER_NOT_EXIST_401);
                    return payload;
                }
                if (isLocked) {
                    payload.setCode(Commons.ERR_WEB_USER_IS_LOCKED_402);
                    return payload;
                }
                payload.setUserToken(param);
                break;
            case LoginToken:
                if (!isExist) {
                    payload.setCode(Commons.ERR_WEB_USERTOKEN_NOT_EXIST_403);
                    return payload;
                }
                if (isLocked) {
                    payload.setCode(Commons.ERR_WEB_USER_IS_LOCKED_402);
                    return payload;
                }
                payload.setLoginToken(param);
                break;
        }

        payload.setCode(Commons.SUCCESS_WEB);
        return payload;
    }

    public enum TokenType {
        UserToken,
        LoginToken
    }
}
