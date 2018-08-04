package com.billding.meta;

public class CumulativeTestResults {
    private final TestingPeriod testingPeriod;
    private final Organization organization;

    public CumulativeTestResults(
            TestingPeriod testingPeriod,
            Organization organization
    ) {
        this.testingPeriod = testingPeriod;
        this.organization = organization;
    }

    public int getNumberOfTimesTestWillBeRun() {
        return
                this.testingPeriod.getDeveloperHours()
                        * organization.getNumberOfDevelopers()
                        * this.testingPeriod.getFunkyDaysNumber();
    }
}
