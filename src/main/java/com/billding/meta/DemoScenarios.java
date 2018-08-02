package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

// The static-ness of this file makes it harder to get logs/trackstaces about causes for failure.
class DemoScenarios {
    private static final PropertyRetriever propertyRetriever = new PropertyRetriever("live_demo");

    static Organization getTestEnvironment() {
        return TEST_ENVIRONMENT;
    }

    private static final Organization TEST_ENVIRONMENT =
        new Organization(
            propertyRetriever.getString("test_environment")
        );

    public static CodeBase getCodeBase() {
        return CODE_BASE;
    }

    private static final CodeBase CODE_BASE =
        new CodeBase(
            propertyRetriever.getString("codebase")
        );

    private static final World getWorld() {
        String worldType = propertyRetriever.getString("world");
        switch (worldType) {
            case "chaotic":
                return new ChaoticWorld();
            case "platonic":
                throw new RuntimeException("Haven't created platonic world yet.");
            default:
                throw new IllegalArgumentException("Not a valid world type");
        }
    }
}
