package org.kevin.web;

import org.kevin.web.base.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController extends ControllerBase {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String indexPage() {
        return mCommonUtility.getSystemVersion();
    }
}
