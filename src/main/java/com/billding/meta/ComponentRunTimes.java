package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

import java.time.Duration;

public class ComponentRunTimes {
    // TODO Make entries for all components
    private final Duration mapper;
    private final Duration database;
    private final Duration producer;

    private final Duration network;
    private final Duration intranet;
    private final Duration logic;
    private final Duration authService;
    private final Duration controller;
    private final Duration application;
    private final Duration seleniumTest;
    private final Duration thirdPartyResource;

    private final Duration browser;
    private final Duration kafkaCluster;
    private final Duration webDriver;

    // TODO Consider improved error msg for missing property.
    public ComponentRunTimes(String propertyFileName) {
        PropertyRetriever propertyRetriever = new PropertyRetriever(propertyFileName);
        this.mapper = propertyRetriever.getShortDuration("mapper");
        this.database = propertyRetriever.getShortDuration("database");
        this.network = propertyRetriever.getShortDuration("network");
        this.intranet = propertyRetriever.getShortDuration("intranet");
        this.logic = propertyRetriever.getShortDuration("logic");
        this.authService = propertyRetriever.getShortDuration("auth_service");
        this.controller = propertyRetriever.getShortDuration("controller");
        this.seleniumTest = propertyRetriever.getShortDuration("selenium_test");
        this.application = propertyRetriever.getShortDuration("application");
        // TODO consider kafka prefix
        this.producer = propertyRetriever.getShortDuration("producer");
        this.thirdPartyResource = propertyRetriever.getShortDuration("third_party_resource");
        this.browser = propertyRetriever.getShortDuration("browser");
        this.kafkaCluster = propertyRetriever.getShortDuration("kafka_cluster");
        this.webDriver = propertyRetriever.getShortDuration("web_driver");
    }

    public Duration getMapper() {
        return mapper;
    }

    public Duration getDatabase() {
        return database;
    }

    public Duration getProducer() {
        return producer;
    }

    public Duration getNetwork() {
        return network;
    }

    public Duration getIntranet() {
        return intranet;
    }

    public Duration getLogic() {
        return logic;
    }

    public Duration getAuthService() {
        return authService;
    }

    public Duration getController() {
        return controller;
    }

    public Duration getSeleniumTest() {
        return seleniumTest;
    }

    public Duration getApplication() {
        return application;
    }

    public Duration getThirdPartyResource() {
        return thirdPartyResource;
    }

    public Duration getBrowser() {
        return browser;
    }

    public Duration getKafkaCluster() {
        return kafkaCluster;
    }

    public Duration getWebDriver() {
        return webDriver;
    }
}
