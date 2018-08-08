package com.billding.meta;

import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * This class will generate the instances that feed to the tests, allowing us to easily scale the
 * number of tests that will execute in a given circumstance.
 */
public class TestInstanceCreator {
    public Object[][] createInstances(Function<CodeBase, Integer> getNumberOfTests, Function<Integer, Object> instanceSupplier) {
        // Currently, this is the single point of control for which environment we want to load.
        final Organization organization = DemoScenarios.getTestEnvironment();
        final CodeBase codeBase = DemoScenarios.getCodeBase();
        return IntStream.range(0, getNumberOfTests.apply(codeBase) * organization.getNumberOfDevelopers() )
            .mapToObj(idx -> new Object[]{
                organization.getDeveloper(idx % organization.getNumberOfDevelopers()),
                instanceSupplier.apply(idx)
            }).toArray(size -> new Object[size][2]);
    }
}
