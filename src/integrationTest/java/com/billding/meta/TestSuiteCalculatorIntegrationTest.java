package com.billding.meta;

import org.testng.annotations.Test;

public class TestSuiteCalculatorIntegrationTest {
    @Test
    public void test_basic() {
        final TestEnvironment testEnvironment = new TestEnvironment("solo_project");
        final CodeBase codeBase = new CodeBase("minimal");
        final InstanceGroup instanceGroup = new InstanceGroupRealTimes();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                testEnvironment,
                codeBase,
                instanceGroup);
        System.out.println(
                "Runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
    }
}
