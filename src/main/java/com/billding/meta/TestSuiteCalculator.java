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
    private final ComponentRunTimes componentRunTimes;
    private final CodeBase codeBase;
    private final InstanceGroup instanceGroup;

    public TestSuiteCalculator(TestEnvironment testEnvironment, ComponentRunTimes componentRunTimes, CodeBase codeBase, InstanceGroup instanceGroup) {
        this.testEnvironment = testEnvironment;
        this.componentRunTimes = componentRunTimes;
        this.codeBase = codeBase;
        this.instanceGroup = instanceGroup;
    }

    // TODO Make sure this is exhaustive.
    // this is wrong, because we're just getting the values from any one layer, it's not calling
    // getRunTime and using the costs of the dependencies
    public Duration totalTestRunTime() {
                Duration currentlyIncorrectCalculations =
            this.instanceGroup.getMapper().getRunTime().multipliedBy(this.codeBase.getNumberOfMapperTests())
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
