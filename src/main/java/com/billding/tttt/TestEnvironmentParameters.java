package com.billding.tttt;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// By removing reliance on @Test(invocationCount), we can make this a normal class without any static reliance.
// Then I can make new instances to demonstrate different real-world circumstances.
final class TestEnvironmentParameters {
    private final int numberOfApplicationTests;
    private final int numberOfMapperTests;
    private final int numberOfControllerTests;
    private final int numberOfThirdPartyResourceTests;

    private final List<String> developers;

    private final int runsPerDeveloperPerHour;
    private final int numberOfHours;
    private final int numberOfDevelopers;
    private final int numberOfTimesTestWillBeRun;


    // TODO Consider taking property file name here, rather than demanding on instance from the caller
    public TestEnvironmentParameters() {
        final PropertyRetriever propertyRetriever = new PropertyRetriever("test_environment");
        this.numberOfApplicationTests = propertyRetriever.getInt("num_tests.application");
        this.numberOfMapperTests = propertyRetriever.getInt("num_tests.mappers");
        this.numberOfControllerTests = propertyRetriever.getInt("num_tests.controllers");
        this.numberOfThirdPartyResourceTests = propertyRetriever.getInt("num_tests.third_party_resources");

        this.developers = parseDevelopers(propertyRetriever.getString("developers"));
        this.numberOfDevelopers = developers.size();
        this.numberOfHours = propertyRetriever.getInt("hours_in_work_day");
        this.runsPerDeveloperPerHour = propertyRetriever.getInt("runs_per_developer_per_hour");
        this.numberOfTimesTestWillBeRun = runsPerDeveloperPerHour * numberOfHours * numberOfDevelopers;
    }

    private final Random random = new Random();
    public String getRandomDeveloper() {
        return developers.get(Math.abs(random.nextInt()) % developers.size());
    }

    int getNumberOfApplicationTests() {
        return numberOfApplicationTests * getNumberOfTimesTestWillBeRun();
    }

    int getNumberOfMapperTests() {
        return numberOfMapperTests * getNumberOfTimesTestWillBeRun();
    }

    private List<String> parseDevelopers(String csvList) {
        final String[] split = csvList.split(",");
        return Arrays.asList(split);
    }

    public int getNumberOfTimesTestWillBeRun() {
        return numberOfTimesTestWillBeRun;
    }

    public int getTotalNumberOfCasesRun() {
        return
            this.getNumberOfMapperTests()
            + this.getNumberOfApplicationTests()
            + this.getNumberOfControllerTests()
            + this.getNumberOfThirdPartyResourceTests();
    }

    public int getNumberOfControllerTests() {
        return numberOfControllerTests * getNumberOfTimesTestWillBeRun();
    }

    public int getNumberOfThirdPartyResourceTests() {
        return numberOfThirdPartyResourceTests * getNumberOfTimesTestWillBeRun();
    }
}