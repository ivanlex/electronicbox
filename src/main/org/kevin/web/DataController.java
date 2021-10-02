package org.kevin.web;

import org.kevin.domain.MCU;
import org.kevin.service.DataService;
import org.kevin.web.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController extends ControllerBase {

    DataService mDataService;

    @Autowired
    private void setDataService(DataService dataService) {
        mDataService = dataService;
    }

    @RequestMapping(path = "getMCUs", method = RequestMethod.POST)
    List<MCU> getMCUs(@RequestParam String LoginToken) {
        return mDataService.getMCUs(LoginToken);
    }

    boolean resetMCU(String mcuId) {
        //TODO resetMCU

        return true;
    }

}
