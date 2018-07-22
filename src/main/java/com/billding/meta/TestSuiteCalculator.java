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

    // TODO One possibility for simplifying the calculations below. Doesn't look worth it right now.
    private class TestGroup {
        private final UnreliableService service;
        private final int testCount;

        private TestGroup(UnreliableService service, int testCount) {
            this.service = service;
            this.testCount = testCount;
        }

        Duration totalTime() {
            return this.service.getRunTime().multipliedBy(this.testCount);

        }
    }

    private Duration runTimeFor(UnreliableService service, int testCount) {
        return service.getRunTime().multipliedBy(testCount);
    }

    // TODO Make sure this is exhaustive.
    public Duration totalTestRunTime() {
        runTimeFor(instanceGroup.getMapper(), codeBase.getNumberOfMapperTests())
                .plus(runTimeFor(instanceGroup.getLogic(), codeBase.getNumberOfLogicTests()))
                .plus(runTimeFor(instanceGroup.getProducer(), codeBase.getNumberOfProducerTests())
                .plus(runTimeFor(instanceGroup.getApplication(), codeBase.getNumberOfApplicationTests()))
                .plus(runTimeFor(instanceGroup.getController(), codeBase.getNumberOfControllerTests()))
                .plus(runTimeFor(instanceGroup.getGithub(), codeBase.getNumberOfThirdPartyResourceTests())))
        ;

                Duration currentlyIncorrectCalculations =
                instanceGroup.getMapper().getRunTime().multipliedBy(codeBase.getNumberOfMapperTests())
                .plus(instanceGroup.getLogic().getRunTime().multipliedBy(codeBase.getNumberOfLogicTests()))
                .plus(instanceGroup.getProducer().getRunTime().multipliedBy(codeBase.getNumberOfProducerTests())
                .plus(instanceGroup.getApplication().getRunTime().multipliedBy(codeBase.getNumberOfApplicationTests()))
                .plus(instanceGroup.getController().getRunTime().multipliedBy(codeBase.getNumberOfControllerTests()))
                .plus(instanceGroup.getGithub().getRunTime().multipliedBy(codeBase.getNumberOfThirdPartyResourceTests())))
            ;
        return currentlyIncorrectCalculations;
    }

    public Duration runTimeDuringWindow() {
        return totalTestRunTime()
                .multipliedBy(this.testEnvironment.getNumberOfTimesTestWillBeRun());
    }
}
