package com.billding.meta;

import com.billding.meta.TestEnvironment;

import java.util.function.Function;
import java.util.stream.IntStream;

public class TestInstanceCreator {
    public Object[][] createInstances(Function<TestEnvironment, Integer> getNumberOfTests, Function<Integer, Object> instanceSupplier) {
        final TestEnvironment testEnvironment = new TestEnvironment();
        return IntStream.range(0, getNumberOfTests.apply(testEnvironment))
            .mapToObj(idx -> new Object[]{
                testEnvironment.getRandomDeveloper(),
                instanceSupplier.apply(idx)
            }).toArray(size -> new Object[size][2]);
    }
}