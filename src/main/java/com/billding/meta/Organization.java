package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// TODO make new instances to demonstrate different real-world circumstances.
public final class Organization {
    private final String name;
    private final List<String> developers;

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    private final int numberOfDevelopers;

    public Organization(String propertyFileName) {
        this.name = propertyFileName;

        final PropertyRetriever propertyRetriever = new PropertyRetriever("test_environments/" + propertyFileName);
        this.developers = parseDevelopers(propertyRetriever.getString("developers"));
        this.numberOfDevelopers = developers.size();
        int numberOfHours = propertyRetriever.getInt("hours_in_work_day");
        int runsPerDeveloperPerHour = propertyRetriever.getInt("runs_per_developer_per_hour");
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

    public String getName() {
        return name;
    }
}