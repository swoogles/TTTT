package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TestSuiteCalculatorTest {
    @Test
    public void test_totalTestRunTime() {
        TestEnvironment testEnvironment = new TestEnvironment("solo_project");
        CodeBase codeBase = new CodeBase("minimal");
        ComponentRunTimes componentRunTimes = new ComponentRunTimes("frozen_runtimes");
        InstanceGroup instanceGroup = new InstanceGroup();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(testEnvironment, componentRunTimes, codeBase, instanceGroup);
        assertEquals(testSuiteCalculator.totalTestRunTime(), Duration.ofSeconds(3).plusMillis(100));
        assertEquals(testSuiteCalculator.runTimeDuringWindow(), Duration.ofMinutes(28).plusSeconds(56));
    }
}
