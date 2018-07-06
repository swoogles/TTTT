package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// TODO make new instances to demonstrate different real-world circumstances.
public final class TestEnvironment {
    private final List<String> developers;

    private final int runsPerDeveloperPerHour;
    private final int numberOfHours;

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    private final int numberOfDevelopers;
    private final int numberOfTimesTestWillBeRun;


    public TestEnvironment(String propertyFileName) {
        // TODO Consider less Stringy files.
        final PropertyRetriever propertyRetriever = new PropertyRetriever("test_environments/" + propertyFileName);
        this.developers = parseDevelopers(propertyRetriever.getString("developers"));
        this.numberOfDevelopers = developers.size();
        this.numberOfHours = propertyRetriever.getInt("hours_in_work_day");
        this.runsPerDeveloperPerHour = propertyRetriever.getInt("runs_per_developer_per_hour");
        this.numberOfTimesTestWillBeRun = runsPerDeveloperPerHour * numberOfHours * numberOfDevelopers;
    }

    private final Random random = new Random();
    // TODO Consider parameterized Random
    public String getRandomDeveloper() {
        return developers.get(Math.abs(random.nextInt() & Integer.MAX_VALUE) % developers.size());
    }

    private List<String> parseDevelopers(String csvList) {
        final String[] split = csvList.split(",");
        return Arrays.asList(split);
    }

}