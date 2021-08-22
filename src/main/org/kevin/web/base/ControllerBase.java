package org.kevin.web.base;

import org.kevin.utility.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ControllerBase {

    @Autowired
    public CommonUtility mCommonUtility;
}
