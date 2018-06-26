package com.billding.tttt;

public class Browser implements UnreliableService {
    private final int operationRunTime = 30;
    private static final String name = "browser";

    public Browser() { }

    @Override
    public int getOperationRunTime() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @Override
    public int failableAction() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }
}
