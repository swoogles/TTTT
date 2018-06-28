package com.billding.tttt;

import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.ThirdPartyResource;

public class Application implements UnreliableService {
    private static final String SERVICE_NAME_BASE = "application";
    private final int operationRunTime;

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
        int operationRunTime
    ){
        this.name = SERVICE_NAME_BASE + "_" + name;
        this.operationRunTime = operationRunTime;
        this.kafkaCluster = kafkaCluster;
        this.authService = authService;
        this.controller = controller;
        this.github = github;
    }

    int simpleAction() {
        return this.failableAction();
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        int numPatients = 5;
        ServiceStatus.ensureServiceIsRunning(SERVICE_NAME_BASE);
        return this.getOperationRunTime()
            + kafkaCluster.clusterAction()
            + authService.authenticateUser("userName", "password")
            + controller.facilityLevelOperation("facilityId", numPatients)
            + github.communicate();
    }
}
