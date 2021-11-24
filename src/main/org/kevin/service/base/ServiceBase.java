package org.kevin.service.base;

import org.kevin.utility.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceBase {
    @Autowired
    public CommonUtility mCommonUtility;
}
