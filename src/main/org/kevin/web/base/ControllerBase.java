package org.kevin.web.base;

import org.kevin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ControllerBase {

    @Autowired
    public Utility mUtility;
}
