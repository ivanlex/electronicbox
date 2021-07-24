package org.kevin.common;

public class Commons {
    public static final String EMPTY_STRING = "";
    public static final String PAYLOAD_SPLIT = "|";
    public static final String PROPERTY_SPLIT = ":";
    public static final String[] RequestTypeEnums = {"LU", "DU"};
    public static final String[] DataUploadKeys = {"LSC", "OS", "GS", "SC"};
    public static final String PAYLOAD_END = "<EOF>";

    public static final String KEY_ERR_CODE = "errCode";
    public static final String KEY_MCU_ID = "MID";
    public static final String KEY_MCU_PWD = "MWD";
    public static final String KEY_MCU_TOKEN = "MTK";
    public static final String KEY_MCU_REBOOT = "RST";

    public static final String VALUE_MCU_NEED_REBOOT = "1";
    public static final String VALUE_MCU_DONT_REBOOT = "0";

    public static final String ERR_SOCKET_FORMAT_401 = "401";
    public static final String ERR_SOCKET_UNKNOWN_REQUEST_TYPE_402 = "402";
    public static final String ERR_SOCKET_KEYS_INCOMPLETE_403 = "403";
    public static final String ERR_SOCKET_MCU_NOT_EXIST_404 = "404";
    public static final String ERR_SOCKET_MCU_NOT_VALID_405 = "405";
    public static final String ERR_SOCKET_MCU_TOKEN_NOT_VALID_406 = "406";
}
