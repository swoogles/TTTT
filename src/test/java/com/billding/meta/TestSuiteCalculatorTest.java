package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

/* TODO This class should use frozen values, as I will continue to tinker with all
    values in the various codebases/testenvironments/runtimes
 */
public class TestSuiteCalculatorTest {
    @Test
    public void test_totalTestRunTime() {
        Organization organization = new Organization("solo_project");
        CodeBase codeBase = new CodeBase("minimal");
        TestingPeriod testingPeriod = new TestingPeriod("moment");
        InstanceGroup instanceGroup = new InstanceGroupRealTimes();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(organization, codeBase, testingPeriod, instanceGroup);
        assertEquals(testSuiteCalculator.totalTestRunTime(), Duration.ofSeconds(4).plusMillis(70));
        assertEquals(testSuiteCalculator.runTimeDuringWindow(), Duration.ofSeconds(4).plusMillis(70));
    }

    @Test
    public void test_totalTestRunTime_maximum() {
        Organization organization = new Organization("midsized");
        CodeBase codeBase = new CodeBase("established");
        TestingPeriod testingPeriod = new TestingPeriod("long_term");
        InstanceGroup instanceGroup = new InstanceGroupRealTimes();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(organization, codeBase, testingPeriod, instanceGroup);
        assertEquals(testSuiteCalculator.totalTestRunTime(), Duration.ofSeconds(25).plusMillis(725));
        assertEquals(testSuiteCalculator.runTimeDuringWindow(), Duration.ofHours(2160).plusMinutes(54));
    }
}
