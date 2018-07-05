package com.billding.meta;

import java.util.function.Function;
import java.util.stream.IntStream;

public class TestInstanceCreator {
    public Object[][] createInstances(Function<CodeBase, Integer> getNumberOfTests, Function<Integer, Object> instanceSupplier) {
        // Currently, this is the single point of control for which environment we want to load.
        final TestEnvironment testEnvironment = DemoScenarios.getTestEnvironment();
        final CodeBase codeBase = DemoScenarios.getCodeBase();
        return IntStream.range(0, getNumberOfTests.apply(codeBase))
            .mapToObj(idx -> new Object[]{
                testEnvironment.getRandomDeveloper(),
                instanceSupplier.apply(idx)
            }).toArray(size -> new Object[size][2]);
    }
}
