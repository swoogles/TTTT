package com.billding.meta;

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
    public int totalTestRunTime() {
        return
            this.codeBase.getNumberOfMapperTests() * this.componentRunTimes.getMapper()
                + this.codeBase.getNumberOfProducerTests() * this.componentRunTimes.getProducer()
                + this.codeBase.getNumberOfApplicationTests() * this.componentRunTimes.getApplication()
                + this.codeBase.getNumberOfControllerTests() * this.componentRunTimes.getController()
                + this.codeBase.getNumberOfThirdPartyResourceTests() * this.componentRunTimes.getThirdPartyResource()
            ;
    }
}
