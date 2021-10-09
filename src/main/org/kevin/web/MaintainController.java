package org.kevin.web;

import org.kevin.domain.reqres.web.MCUAddRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaintainController {

    @RequestMapping(path = "mcuAdd",method = RequestMethod.POST)
    public void addDevice(@RequestBody MCUAddRequest request){

    }
}
