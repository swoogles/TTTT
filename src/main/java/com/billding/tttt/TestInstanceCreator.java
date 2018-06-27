package com.billding.tttt;

import java.util.function.Function;
import java.util.stream.IntStream;

public class TestInstanceCreator {
    Object[][] createInstances(Function<TestEnvironmentParameters, Integer> getNumberOfTests, Function<Integer, Object> instanceSupplier) {
        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();
        return IntStream.range(0, getNumberOfTests.apply(testEnvironmentParameters))
            .mapToObj(idx -> new Object[]{
                testEnvironmentParameters.getRandomDeveloper(),
                instanceSupplier.apply(idx)
            }).toArray(size -> new Object[size][2]);
    }
}