package com.billding.meta;

import com.billding.tttt.*;
import com.billding.tttt.external_services.ThirdPartyResource;

public interface InstanceGroup {
    Mapper getMapper();

    Logic getLogic();

    Controller getController();

    ThirdPartyResource getGithub();

    Application getApplication();

    Producer getProducer();

    SeleniumTestClass getSeleniumTestClass();
}
