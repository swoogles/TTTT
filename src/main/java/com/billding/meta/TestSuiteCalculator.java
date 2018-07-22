package com.billding.meta;

import com.billding.tttt.UnreliableService;

import java.time.Duration;

/**
 * Will look at the combination of properties that define a scenario and determine
 * how many tests would be run in that circumstance.
 *
 * Note- Doesn't include any overhead factor for the tests themselves
 */
public class TestSuiteCalculator {
    private final TestEnvironment testEnvironment;
    private final CodeBase codeBase;
    private final InstanceGroup instanceGroup;

    public TestSuiteCalculator(TestEnvironment testEnvironment, CodeBase codeBase, InstanceGroup instanceGroup) {
        this.testEnvironment = testEnvironment;
        this.codeBase = codeBase;
        this.instanceGroup = instanceGroup;
    }

    private Duration runTimeFor(UnreliableService service, int testCount) {
        return service.getRunTime().multipliedBy(testCount);
    }

    // TODO Make sure this is exhaustive.
    public Duration totalTestRunTime() {
        return runTimeFor(instanceGroup.getMapper(), codeBase.getNumberOfMapperTests())
                .plus(runTimeFor(instanceGroup.getLogic(), codeBase.getNumberOfLogicTests()))
                .plus(runTimeFor(instanceGroup.getProducer(), codeBase.getNumberOfProducerTests())
                .plus(runTimeFor(instanceGroup.getApplication(), codeBase.getNumberOfApplicationTests()))
                .plus(runTimeFor(instanceGroup.getController(), codeBase.getNumberOfControllerTests()))
                .plus(runTimeFor(instanceGroup.getGithub(), codeBase.getNumberOfThirdPartyResourceTests())))
                .plus(runTimeFor(instanceGroup.getSeleniumTestClass(), codeBase.getNumberOfSeleniumTests()))
        ;
    }

    public Duration runTimeDuringWindow() {
        return totalTestRunTime()
                .multipliedBy(this.testEnvironment.getNumberOfTimesTestWillBeRun());
    }
}
