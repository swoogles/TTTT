package com.billding.meta;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

// TODO These are important examples. Make sure to highlight them in presentation.
public class TestSuiteCalculatorIntegrationTest {
    @DataProvider(name = "scenarios")
    public static Object[][] logicInstances() {
        Stream<Object[]> scenarios = Stream.of(
                "solo_project", "startup", "midsized")
                .map(TestEnvironment::new)
                .flatMap(environment -> {
                            return Stream.of(
                                    "minimal", "adolescent", "established")
                                    .map(CodeBase::new)
                                    .flatMap(codeBase -> {
                                        return Stream.of(
                                                new InstanceGroupMockTimes(),
                                                new InstanceGroupRealTimes()
                                        ).map(instanceGroup -> new Object[]{environment, codeBase, instanceGroup});

                                    });
                        }

                );

        return scenarios
                .toArray(size -> new Object[size][2]);
    }


    @Test(dataProvider = "scenarios")
    public void test_scenarios_mocked(TestEnvironment testEnvironment, CodeBase codeBase, InstanceGroup instanceGroup) {
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                testEnvironment,
                codeBase,
                instanceGroup
        );
        // This isn't factoring in environment AT ALL. Only codebases and the instances.
        /*
        System.out.println(
                "environment: " + testEnvironment.getName()
                + " codebase: " + codeBase.getName()
                + " single runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
        */

        System.out.println(
                "environment: " + testEnvironment.getName()
                        + " codebase: " + codeBase.getName()
                        + " instances: " + instanceGroup.getName()
                        + " runtime in window: "  + testSuiteCalculator.runTimeDuringWindow()
        );
    }

    @Test
    public void mockInstances_tiny() {
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                new TestEnvironment("solo_project"),
                new CodeBase("minimal"),
                new InstanceGroupMockTimes()
        );
        System.out.println(
                "Mock instances tiny runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
    }

    @Test
    public void realInstances_tiny() {
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                new TestEnvironment("solo_project"),
                new CodeBase("minimal"),
                new InstanceGroupRealTimes()
        );
        System.out.println(
                "Real instances tiny runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
    }
}
