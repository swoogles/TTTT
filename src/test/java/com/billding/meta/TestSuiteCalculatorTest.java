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
        InstanceGroup instanceGroup = new InstanceGroupRealTimes();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(organization, codeBase, instanceGroup);
        assertEquals(testSuiteCalculator.totalTestRunTime(), Duration.ofSeconds(4).plusMillis(70));
        assertEquals(testSuiteCalculator.runTimeDuringWindow(), Duration.ofMinutes(37).plusSeconds(59).plusMillis(200));
    }

    @Test
    public void test_totalTestRunTime_maximum() {
        Organization organization = new Organization("midsized");
        CodeBase codeBase = new CodeBase("established");
        InstanceGroup instanceGroup = new InstanceGroupRealTimes();
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(organization, codeBase, instanceGroup);
        assertEquals(testSuiteCalculator.totalTestRunTime(), Duration.ofSeconds(25).plusMillis(725));
        assertEquals(testSuiteCalculator.runTimeDuringWindow(), Duration.ofHours(2160).plusMinutes(54));
    }
}
