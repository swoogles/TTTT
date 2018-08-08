package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

public class ServiceStatus {
    // TODO ground this static junk
    private static PropertyRetriever propertyRetriever =
            new PropertyRetriever("service_status");

    public static void ensureServiceIsRunning(String name) {
        if (propertyRetriever.getBinaryBoolean(name) == false)
            throw new RuntimeException("Good luck figuring this one out.");
//        throw new RuntimeException(name + " failed us!");
    }
}
