package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

// The static-ness of this file makes it harder to get logs/trackstaces about causes for failure.
class DemoScenarios {
    private static final PropertyRetriever propertyRetriever = new PropertyRetriever("live_demo");

    static TestEnvironment getTestEnvironment() {
        return TEST_ENVIRONMENT;
    }

    private static final TestEnvironment TEST_ENVIRONMENT =
        new TestEnvironment(
            propertyRetriever.getString("test_environment")
        );

    public static CodeBase getCodeBase() {
        return CODE_BASE;
    }

    private static final CodeBase CODE_BASE =
        new CodeBase(
            propertyRetriever.getString("codebase")
        );
}
