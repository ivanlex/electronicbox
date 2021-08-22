package org.kevin.service.interfaces;

import org.kevin.domain.MCU;

public interface LoginServiceInterface {
    boolean isMCUExist(MCU mcu);

    boolean isMCUValid(MCU mcu);

    boolean isMCUTokenValid(MCU mcu);

    boolean isMCURequireReboot(MCU mcu);

    String createMCUToken(MCU mcu);

    boolean isUserExist(String username);

}
