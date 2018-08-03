package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

// The static-ness of this file makes it harder to get logs/trackstaces about causes for failure.
public class DemoScenarios {
    private static final PropertyRetriever propertyRetriever = new PropertyRetriever("live_demo");

    static Organization getTestEnvironment() {
        return TEST_ENVIRONMENT;
    }

    public static boolean shouldUseRealRuntimes() {
        return useRealRuntimes;
    }

    private static final boolean useRealRuntimes =
            propertyRetriever.getBoolean("use_real_runtimes");

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

    public static World getWorld() {
        String worldType = propertyRetriever.getString("world");
        return World.getWorld(worldType);
    }

    public static World getPlatonicWorld() {
        return new PlatonicWorld();
    }

}
