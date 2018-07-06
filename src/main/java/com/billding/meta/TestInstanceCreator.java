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
        final TestEnvironment testEnvironment = DemoScenarios.getTestEnvironment();
        final CodeBase codeBase = DemoScenarios.getCodeBase();
        return IntStream.range(0, getNumberOfTests.apply(codeBase) * testEnvironment.getNumberOfDevelopers() )
            .mapToObj(idx -> new Object[]{
                testEnvironment.getRandomDeveloper(),
                instanceSupplier.apply(idx)
            }).toArray(size -> new Object[size][2]);
    }
}
