package com.billding.meta;

public class TestSuiteCalculator {
    private final TestEnvironment testEnvironment;
    private final ComponentRunTimes componentRunTimes;

    public TestSuiteCalculator(TestEnvironment testEnvironment, ComponentRunTimes componentRunTimes) {
        this.testEnvironment = testEnvironment;
        this.componentRunTimes = componentRunTimes;
    }

    // TODO Make sure this is exhaustive.
    public int totalTestRunTime() {
        return
            this.testEnvironment.getNumberOfMapperTests() * this.componentRunTimes.getMapper()
                + this.testEnvironment.getNumberOfProducerTests() * this.componentRunTimes.getProducer()
                + this.testEnvironment.getNumberOfApplicationTests() * this.componentRunTimes.getApplication()
                + this.testEnvironment.getNumberOfControllerTests() * this.componentRunTimes.getController()
                + this.testEnvironment.getNumberOfThirdPartyResourceTests() * this.componentRunTimes.getThirdPartyResource()
            ;
    }
}
