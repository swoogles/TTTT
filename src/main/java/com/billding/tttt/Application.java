package com.billding.tttt;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.ThirdPartyResource;

import java.time.Duration;

public class Application implements UnreliableService {
    private static final String SERVICE_NAME_BASE = "application";
    private final Duration runTime;

    private final String name;

    private final Producer producer;
    private final AuthService authService;
    private final Controller controller;
    private final ThirdPartyResource github;

    // TODO Should probably only care about controller instances at this level.
    public Application(
        String name,
        Producer producer,
        AuthService authService,
        Controller controller,
        ThirdPartyResource github,
        Duration runTime
    ){
        this.name = SERVICE_NAME_BASE + "_" + name;
        this.runTime = runTime;
        this.producer = producer;
        this.authService = authService;
        this.controller = controller;
        this.github = github;
    }

    Duration simpleAction() {
        return this.failableAction();
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(producer.getRunTime())
            .plus(authService.getRunTime())
            .plus(controller.getRunTime())
            .plus(github.getRunTime());
    }

    @Override
    public Duration failableAction() {
        ServiceStatus.ensureServiceIsRunning(SERVICE_NAME_BASE);
        return this.getRunTime()
            .plus(producer.submitEvent())
            .plus(authService.authenticateUser("userName", "password"))
            .plus(controller.facilityLevelOperation())
            .plus(github.communicate());
    }
}
