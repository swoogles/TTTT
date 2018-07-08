package com.billding.meta;

import java.time.Duration;

/**
 * Will look at the combination of properties that define a scenario and determine
 * how many tests would be run in that circumstance.
 */
public class TestSuiteCalculator {
    private final TestEnvironment testEnvironment;
    private final ComponentRunTimes componentRunTimes;
    private final CodeBase codeBase;

    public TestSuiteCalculator(TestEnvironment testEnvironment, ComponentRunTimes componentRunTimes, CodeBase codeBase) {
        this.testEnvironment = testEnvironment;
        this.componentRunTimes = componentRunTimes;
        this.codeBase = codeBase;
    }

    // TODO Make sure this is exhaustive.
    // this is wrong, because we're just getting the values from any one layer, it's not calling
    // getRunTime and using the costs of the dependencies
    public Duration totalTestRunTime() {
                Duration currentlyIncorrectCalculations =
            this.componentRunTimes.getMapper().multipliedBy(this.codeBase.getNumberOfMapperTests())
                .plus(this.componentRunTimes.getProducer().multipliedBy(this.codeBase.getNumberOfProducerTests())
                .plus(this.componentRunTimes.getApplication().multipliedBy(this.codeBase.getNumberOfApplicationTests()))
                .plus(this.componentRunTimes.getController().multipliedBy(this.codeBase.getNumberOfControllerTests()))
                .plus(this.componentRunTimes.getThirdPartyResource().multipliedBy(this.codeBase.getNumberOfThirdPartyResourceTests())))
            ;
        return currentlyIncorrectCalculations;
    }
}
