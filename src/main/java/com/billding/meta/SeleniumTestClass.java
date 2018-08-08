package com.billding.meta;

import com.billding.tttt.AbstractUnreliableService;
import com.billding.tttt.Application;
import com.billding.tttt.Browser;
import com.billding.tttt.external_services.ThirdPartyResource;
import com.billding.tttt.external_services.WebDriver;

import java.time.Duration;

public class SeleniumTestClass extends AbstractUnreliableService {
    public SeleniumTestClass(Application application, Browser browser, WebDriver webDriver, ThirdPartyResource javascriptCDN, Duration operationRunTime) {
        super(null, operationRunTime, application, browser, webDriver, javascriptCDN);
    }
}
