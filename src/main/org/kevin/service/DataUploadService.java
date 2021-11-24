package org.kevin.service;

import org.kevin.domain.MCU;
import org.kevin.service.base.ServiceBase;
import org.kevin.service.interfaces.DataUploadServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class DataUploadService extends ServiceBase implements DataUploadServiceInterface {

    @Transactional
    public void uploadDatas(MCU mcu, HashMap<String, String> reqProperties) {

    }
}
