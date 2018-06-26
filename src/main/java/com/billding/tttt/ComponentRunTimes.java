package com.billding.tttt;

public class ComponentRunTimes {
    // TODO Make entries for all components
    private final int mapper;
    private final int producer;

    private final int network;
    private final int logic;
    private final int authService;
    private final int controller;
    private final int application;
    private final int seleniumTest;

    // TODO Consider improved error msg for missing property.
    public ComponentRunTimes() {
        PropertyRetriever propertyRetriever = new PropertyRetriever("runtimes");
        this.mapper = propertyRetriever.getInt("mapper");
        this.network = propertyRetriever.getInt("network");
        this.logic = propertyRetriever.getInt("logic");
        this.authService = propertyRetriever.getInt("auth_service");
        this.controller = propertyRetriever.getInt("controller");
        this.seleniumTest = propertyRetriever.getInt("selenium_test");
        this.application = propertyRetriever.getInt("application");
        // TODO consider kafka prefix
        this.producer = propertyRetriever.getInt("producer");
    }

    public int getMapper() {
        return mapper;
    }

    public int getProducer() {
        return producer;
    }

    public int getNetwork() {
        return network;
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

}
