package com.billding.meta;

public class TestSuiteCalculator {
    private final TestEnvironmentParameters testEnvironmentParameters;
    private final ComponentRunTimes componentRunTimes;

    public TestSuiteCalculator(TestEnvironmentParameters testEnvironmentParameters, ComponentRunTimes componentRunTimes) {
        this.testEnvironmentParameters = testEnvironmentParameters;
        this.componentRunTimes = componentRunTimes;
    }

    // TODO Make sure this is exhaustive.
    public int totalTestRunTime() {
        return
            this.testEnvironmentParameters.getNumberOfMapperTests() * this.componentRunTimes.getMapper()
                + this.testEnvironmentParameters.getNumberOfProducerTests() * this.componentRunTimes.getProducer()
                + this.testEnvironmentParameters.getNumberOfApplicationTests() * this.componentRunTimes.getApplication()
                + this.testEnvironmentParameters.getNumberOfControllerTests() * this.componentRunTimes.getController()
                + this.testEnvironmentParameters.getNumberOfThirdPartyResourceTests() * this.componentRunTimes.getThirdPartyResource()
            ;
    }
}
