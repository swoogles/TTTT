package com.billding.tttt;

public class ServiceStatus {
    // TODO ground this static shiz
    private static PropertyRetriever propertyRetriever = new PropertyRetriever("service_status");

    public static void ensureServiceIsRunning(String name) {
        if (propertyRetriever.getBoolean(name) == false)
            throw new RuntimeException(name + " failed us!");
    }
}
