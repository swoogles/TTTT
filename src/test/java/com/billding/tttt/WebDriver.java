package com.billding.tttt;

public class WebDriver implements UnreliableService {
    private static final String name = "web_driver";
    private final int operationRunTime = 10;

    public WebDriver() { }

    @Override
    public int getOperationRunTime() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @Override
    public int failableAction() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }
}
