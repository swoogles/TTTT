package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestSuiteCalculatorTest {
    @Test
    public void test_totalTestRunTime() {
        TestEnvironment testEnvironment = new TestEnvironment("solo_project");
        CodeBase codeBase = new CodeBase("minimal");
        ComponentRunTimes componentRunTimes = new ComponentRunTimes("frozen_runtimes");
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(testEnvironment, componentRunTimes, codeBase);
        assertEquals(testSuiteCalculator.totalTestRunTime(), 3713);
    }
}
