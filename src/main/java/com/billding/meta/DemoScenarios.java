package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

class DemoScenarios {
    private static final PropertyRetriever propertyRetriever = new PropertyRetriever("live_demo");

    static TestEnvironment getTestEnvironment() {
        return TEST_ENVIRONMENT;
    }

    private static final TestEnvironment TEST_ENVIRONMENT =
        new TestEnvironment(
            propertyRetriever.getString("test_environment")
        );

    private DemoScenarios() {
        // TODO Ensure properties are present.
    }
}
