package com.billding.meta;

import org.testng.annotations.Test;

public class TestSuiteCalculatorIntegrationTest {
    @Test
    public void test_basic() {
        final TestEnvironment testEnvironment =
                new TestEnvironment("solo_project");
        final CodeBase codeBase = new CodeBase("minimal");
        // TODO This is a pretty important example. Make sure to highlight it in presentation.
        // I might also just have pre-determined setups as different cases here, rather
        // than fiddling with the live_demo.properties file.
        final InstanceGroup instanceGroup =
                new InstanceGroupMockTimes();
//                new InstanceGroupRealTimes();

        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                testEnvironment,
                codeBase,
                instanceGroup);
        System.out.println(
                "Runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
    }
}
