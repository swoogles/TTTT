package com.billding.tttt;

import com.billding.meta.ServiceStatus;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.ThirdPartyResource;

import java.time.Duration;

public class Application implements UnreliableService {
    private static final String SERVICE_NAME_BASE = "application";
    private final Duration operationRunTime;

    private final String name;

    private final KafkaCluster kafkaCluster;
    private final AuthService authService;
    private final Controller controller;
    private final ThirdPartyResource github;

    // TODO Should probably only care about controller instances at this level.
    public Application(
        String name,
        KafkaCluster kafkaCluster,
        AuthService authService,
        Controller controller,
        ThirdPartyResource github,
        Duration operationRunTime
    ){
        this.name = SERVICE_NAME_BASE + "_" + name;
        this.operationRunTime = operationRunTime;
        this.kafkaCluster = kafkaCluster;
        this.authService = authService;
        this.controller = controller;
        this.github = github;
    }

    Duration simpleAction() {
        return this.failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        int numPatients = 5;
        ServiceStatus.ensureServiceIsRunning(SERVICE_NAME_BASE);
        return this.getOperationRunTime()
            .plus(kafkaCluster.clusterAction())
            .plus(authService.authenticateUser("userName", "password"))
            .plus(controller.facilityLevelOperation("facilityId", numPatients))
            .plus(github.communicate());
    }
}
