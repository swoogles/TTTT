package com.billding.meta;

import com.billding.tttt.*;
import com.billding.tttt.external_services.*;

import java.time.Duration;

/*
    TODO This class should use real runtimes only at a particular level, but use mocked
    .001ms value for all dependencies.
 */
public class InstanceGroupMockTimes implements InstanceGroup {

    private final Network network;

    private final Producer producer;

    private final Intranet intranet;
    private final AuthService authService;
    private final Database database;
    private final Mapper mapper;
    private final Logic logic;
    private final Controller controller;
    private final ThirdPartyResource github;
    private final Application application;
    private final SeleniumTestClass seleniumTestClass;

    private static final Duration mockRunTime = Duration.ofNanos(10000);

    public InstanceGroupMockTimes() {

        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        this.network = new Network(componentRunTimes.getNetwork());
        Network mockedNetwork = new Network(mockRunTime);

        KafkaCluster kafkaCluster = new KafkaCluster(
                mockedNetwork,
                componentRunTimes.getKafkaCluster()
        );

        KafkaCluster mockedKafkaClusterDependency = new KafkaCluster(
                mockedNetwork,
                mockRunTime
        );

        this.producer =
                new Producer(
                        mockedKafkaClusterDependency,
                        chaoticWorld,
                        componentRunTimes.getProducer()
                );

        this.intranet = new Intranet(
                this.network,
                componentRunTimes.getIntranet());

        this.authService = new AuthService(
                intranet,
                componentRunTimes.getAuthService()
        );
        this.database = new Database(this.network, componentRunTimes.getDatabase());
        Database mockedDatabaseDependency = new Database(mockedNetwork, mockRunTime);
        this.mapper = new Mapper(
                this.database,
                chaoticWorld,
                componentRunTimes.getMapper()
        );
        Mapper mockedMapperDependency = new Mapper(
                mockedDatabaseDependency,
                chaoticWorld,
                mockRunTime
        );
        this.logic = new Logic(
                chaoticWorld,
                mockedMapperDependency,
                componentRunTimes.getLogic()
        );
        Logic mockedLogicDependency = new Logic(
                chaoticWorld,
                mockedMapperDependency,
                mockRunTime
        );
        this.controller = new Controller(
                componentRunTimes.getController(),
                mockedLogicDependency
        );

        Controller mockedControllerDependency = new Controller(
                mockRunTime,
                mockedLogicDependency
        );

        this.github = new ThirdPartyResource(
                "github",
                this.network,
                componentRunTimes.getThirdPartyResource()
        );

        new ThirdPartyResource(
                "github",
                this.network,
                mockRunTime
        );

        this.application = new Application(
                "test_app",
                producer,
                authService,
                mockedControllerDependency,
                github,
                componentRunTimes.getApplication()
        );

        this.seleniumTestClass = new SeleniumTestClass(
                application,
                new Browser(componentRunTimes.getBrowser()),
                new WebDriver(componentRunTimes.getWebDriver()),
                new ThirdPartyResource(
                        "javascript_cdn",
                        this.network,
                        componentRunTimes.getThirdPartyResource()
                ),
                componentRunTimes.getSeleniumTest()
        );

    }

    // TODO reinstate.
//    public AuthService getAuthService() {
//        return authService;
//    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public Logic getLogic() {
        return logic;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public ThirdPartyResource getGithub() {
        return github;
    }

    @Override
    public Application getApplication() {
        return application;
    }

    @Override
    public Producer getProducer() {
        return producer;
    }

    @Override
    public SeleniumTestClass getSeleniumTestClass() {
        return seleniumTestClass;
    }
}
