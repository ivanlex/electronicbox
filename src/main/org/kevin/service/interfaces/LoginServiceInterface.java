package org.kevin.service.interfaces;

import org.kevin.domain.MCUInfo;

public interface LoginServiceInterface {
    boolean isMCUExist(MCUInfo mcuInfo);
}
