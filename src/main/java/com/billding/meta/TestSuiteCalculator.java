package com.billding.meta;

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

    // TODO Make sure this is exhaustive.
    public Duration totalTestRunTime() {
                Duration currentlyIncorrectCalculations =
            this.instanceGroup.getMapper().getRunTime().multipliedBy(this.codeBase.getNumberOfMapperTests())
                    .plus(this.instanceGroup.getLogic().getRunTime().multipliedBy(this.codeBase.getNumberOfLogicTests()))
                .plus(this.instanceGroup.getProducer().getRunTime().multipliedBy(this.codeBase.getNumberOfProducerTests())
                .plus(this.instanceGroup.getApplication().getRunTime().multipliedBy(this.codeBase.getNumberOfApplicationTests()))
                .plus(this.instanceGroup.getController().getRunTime().multipliedBy(this.codeBase.getNumberOfControllerTests()))
                .plus(this.instanceGroup.getGithub().getRunTime().multipliedBy(this.codeBase.getNumberOfThirdPartyResourceTests())))
            ;
        return currentlyIncorrectCalculations;
    }

    public Duration runTimeDuringWindow() {
        return totalTestRunTime()
                .multipliedBy(this.testEnvironment.getNumberOfTimesTestWillBeRun());
    }
}
