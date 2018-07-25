package com.billding.meta;

import com.billding.tttt.*;
import com.billding.tttt.external_services.*;

/*
    TODO This class should use real runtimes only at a particular level, but use mocked
    .001ms value for all dependencies.
 */
public class InstanceGroupRealTimes implements InstanceGroup {

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

    public InstanceGroupRealTimes() {

        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        this.network = new Network(componentRunTimes.getNetwork());
        KafkaCluster kafkaCluster = new KafkaCluster(
                network,
                componentRunTimes.getKafkaCluster()
        );
        this.producer =
                new Producer(
                        kafkaCluster,
                        chaoticWorld,
                        componentRunTimes.getProducer()
                );
        this.intranet = new Intranet(
                network,
                componentRunTimes.getIntranet());

        this.authService = new AuthService(
                intranet,
                componentRunTimes.getAuthService()
        );
        this.database = new Database(network, componentRunTimes.getDatabase());
        this.mapper = new Mapper(
                database,
                chaoticWorld,
                componentRunTimes.getMapper()
        );
        this.logic = new Logic(
                chaoticWorld,
                mapper,
                componentRunTimes.getLogic()
        );
        this.controller = new Controller(
                componentRunTimes.getController(),
                logic
        );
        this.github = new ThirdPartyResource(
                "github",
                network,
                componentRunTimes.getThirdPartyResource()
        );

        this.application = new Application(
                "test_app",
                producer,
                authService,
                controller,
                github,
                componentRunTimes.getApplication()
        );

        this.seleniumTestClass = new SeleniumTestClass(
                application,
                new Browser(componentRunTimes.getBrowser()),
                new WebDriver(componentRunTimes.getWebDriver()),
                new ThirdPartyResource(
                        "javascript_cdn",
                        network,
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
