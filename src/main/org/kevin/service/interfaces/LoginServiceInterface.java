package org.kevin.service.interfaces;

import org.kevin.common.Commons;
import org.kevin.domain.MCUInfo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface LoginServiceInterface {
    boolean isMCUExist(MCUInfo mcuInfo);

    boolean isMCUValid(MCUInfo mcuInfo);

    boolean isMCUTokenValid(MCUInfo mcuInfo);

    boolean isMCURequireReboot(MCUInfo mcuInfo);

    String createMCUToken(MCUInfo mcuInfo);

    boolean isUserExist(String username);

}
