package com.billding.meta;

import com.billding.tttt.*;
import com.billding.tttt.external_services.*;

import java.time.Duration;

/*
    TODO This class should use real runtimes only at a particular level, but use mocked
    .001ms value for all dependencies.
 */
public class InstanceGroupMockTimes implements InstanceGroup {
    @Override
    public String getName() {
        return "Mocks";
    }

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

        final World world = DemoScenarios.getWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        this.network = new Network(componentRunTimes.getNetwork());
        Network mockedNetwork = new Network(mockRunTime);

        KafkaCluster mockedKafkaClusterDependency = new KafkaCluster(
                mockedNetwork,
                mockRunTime
        );

        this.producer =
                new Producer(
                        mockedKafkaClusterDependency,
                        world,
                        componentRunTimes.getProducer()
                );

        Producer mockedProducerDependency = new Producer(
                mockedKafkaClusterDependency,
                world,
                mockRunTime
        );

        this.intranet = new Intranet(
                this.network,
                componentRunTimes.getIntranet());

        Intranet mockedIntranetDependency = new Intranet(
                mockedNetwork,
                mockRunTime);

        this.authService = new AuthService(
                this.intranet,
                componentRunTimes.getAuthService()
        );

        AuthService mockedAuthService = new AuthService(
                mockedIntranetDependency,
                mockRunTime
        );

        this.database = new Database(this.network, componentRunTimes.getDatabase());
        Database mockedDatabaseDependency = new Database(mockedNetwork, mockRunTime);
        this.mapper = new Mapper(
                this.database,
                world,
                componentRunTimes.getMapper()
        );
        Mapper mockedMapperDependency = new Mapper(
                mockedDatabaseDependency,
                world,
                mockRunTime
        );
        this.logic = new Logic(
                world,
                mockedMapperDependency,
                componentRunTimes.getLogic()
        );
        Logic mockedLogicDependency = new Logic(
                world,
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

        ThirdPartyResource mockedGithub = new ThirdPartyResource(
                "github",
                this.network,
                mockRunTime
        );

        this.application = new Application(
                "test_app",
                mockedProducerDependency,
                mockedAuthService,
                mockedControllerDependency,
                mockedGithub,
                componentRunTimes.getApplication()
        );

        Application mockedApplicationDependency = new Application(
                "test_app",
                mockedProducerDependency,
                mockedAuthService,
                mockedControllerDependency,
                mockedGithub,
                mockRunTime
        );

        this.seleniumTestClass = new SeleniumTestClass(
                mockedApplicationDependency,
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
