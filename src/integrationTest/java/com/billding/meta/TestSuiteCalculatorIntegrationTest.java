package com.billding.meta;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

// TODO These are important examples. Make sure to highlight them in presentation.
public class TestSuiteCalculatorIntegrationTest {

    public TestSuiteCalculatorIntegrationTest() {
        System.out.println("|organization | codebase | instances | runtime |");
        System.out.println("|-------|--------|---------|--------|\n");

    }

    @DataProvider(name = "scenarios")
    public static Object[][] logicInstances() {
        Stream<Object[]> scenarios = Stream.of(
                "solo_project", "startup", "midsized")
                .map(Organization::new)
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
    public void test_scenarios_mocked(Organization organization, CodeBase codeBase, InstanceGroup instanceGroup) {
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                organization,
                codeBase,
                instanceGroup
        );
        // This isn't factoring in environment AT ALL. Only codebases and the instances.
        /*
        System.out.println(
                "environment: " + organization.getName()
                + " codebase: " + codeBase.getName()
                + " single runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
        */

        long s = testSuiteCalculator.runTimeDuringWindow().getSeconds();
        String formattedDuration = String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));


        System.out.println(
                "| " + organization.getName()
                        + " | " + codeBase.getName()
                        + " | " + instanceGroup.getName()
                        + " | "  + formattedDuration
                + " |"
        );
    }

}
