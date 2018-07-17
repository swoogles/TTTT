package com.billding.tttt;

import com.billding.tttt.external_services.ThirdPartyResource;

import java.time.Duration;

public class Application extends AbstractUnreliableService {
    private static final String SERVICE_NAME_BASE = "application";

    public Application(
        String name,
        Producer producer,
        AuthService authService,
        Controller controller,
        ThirdPartyResource github,
        Duration runTime
    ){
        // TODO look at servicing different applications
//        super(SERVICE_NAME_BASE + "_" + name, runTime, producer, authService, controller, github);
        super(null, runTime, producer, authService, controller, github);
    }

}
