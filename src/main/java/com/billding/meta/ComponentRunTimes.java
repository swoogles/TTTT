package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

public class ComponentRunTimes {
    // TODO Make entries for all components
    private final int mapper;
    private final int database;
    private final int producer;

    private final int network;
    private final int intranet;
    private final int logic;
    private final int authService;
    private final int controller;
    private final int application;
    private final int seleniumTest;
    private final int thirdPartyResource;

    private final int browser;
    private final int kafkaCluster;
    private final int webDriver;

    // TODO Consider improved error msg for missing property.
    public ComponentRunTimes(String propertyFileName) {
        PropertyRetriever propertyRetriever = new PropertyRetriever(propertyFileName);
        this.mapper = propertyRetriever.getInt("mapper");
        this.database = propertyRetriever.getInt("database");
        this.network = propertyRetriever.getInt("network");
        this.intranet = propertyRetriever.getInt("intranet");
        this.logic = propertyRetriever.getInt("logic");
        this.authService = propertyRetriever.getInt("auth_service");
        this.controller = propertyRetriever.getInt("controller");
        this.seleniumTest = propertyRetriever.getInt("selenium_test");
        this.application = propertyRetriever.getInt("application");
        // TODO consider kafka prefix
        this.producer = propertyRetriever.getInt("producer");
        this.thirdPartyResource = propertyRetriever.getInt("third_party_resource");
        this.browser = propertyRetriever.getInt("browser");
        this.kafkaCluster = propertyRetriever.getInt("kafka_cluster");
        this.webDriver = propertyRetriever.getInt("web_driver");
    }

    public int getMapper() {
        return mapper;
    }

    public int getDatabase() {
        return database;
    }

    public int getProducer() {
        return producer;
    }

    public int getNetwork() {
        return network;
    }

    public int getIntranet() {
        return intranet;
    }

    public int getLogic() {
        return logic;
    }

    public int getAuthService() {
        return authService;
    }

    public int getController() {
        return controller;
    }

    public int getSeleniumTest() {
        return seleniumTest;
    }

    public int getApplication() {
        return application;
    }

    public int getThirdPartyResource() {
        return thirdPartyResource;
    }

    public int getBrowser() {
        return browser;
    }

    public int getKafkaCluster() {
        return kafkaCluster;
    }

    public int getWebDriver() {
        return webDriver;
    }
}
