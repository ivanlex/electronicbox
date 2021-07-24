package org.kevin.service;

import org.kevin.domain.MCUInfo;
import org.kevin.service.interfaces.DataUploadServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class DataUploadService implements DataUploadServiceInterface {

    @Transactional
    public void uploadDatas(MCUInfo mcuInfo, HashMap<String, String> reqProperties) {

    }
}
