package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

public class DemoScenarios {
    private static final PropertyRetriever propertyRetriever = new PropertyRetriever("live_demo");

    public static TestEnvironment getTestEnvironment() {
        return TEST_ENVIRONMENT;
    }

    private static final TestEnvironment TEST_ENVIRONMENT =
        new TestEnvironment(
            propertyRetriever.getString("test_environment")
        );

    public DemoScenarios() {
        // TODO Ensure properties are present.
    }
}
