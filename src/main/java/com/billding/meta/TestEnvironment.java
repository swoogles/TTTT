package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

// TODO make new instances to demonstrate different real-world circumstances.
public final class TestEnvironment {
    private final List<String> developers;

    private final Period timeWindow;

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    private final int numberOfDevelopers;
    private final int numberOfTimesTestWillBeRun;

    public TestEnvironment(String propertyFileName) {
        final PropertyRetriever propertyRetriever = new PropertyRetriever("test_environments/" + propertyFileName);
        this.developers = parseDevelopers(propertyRetriever.getString("developers"));
        this.numberOfDevelopers = developers.size();
        int numberOfHours = propertyRetriever.getInt("hours_in_work_day");
        int runsPerDeveloperPerHour = propertyRetriever.getInt("runs_per_developer_per_hour");
        this.timeWindow = Period.parse(
            propertyRetriever.getString("time_window")

        );
        int monthDays = (int) this.timeWindow.toTotalMonths() * 30;
        int days = this.timeWindow.getDays();
        this.numberOfTimesTestWillBeRun =
                // TODO Improve super janky day calculation
                runsPerDeveloperPerHour * numberOfHours * numberOfDevelopers * (days + monthDays);
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

    public Period getTimeWindow() {
        return timeWindow;
    }

    public int getNumberOfTimesTestWillBeRun() {
        return numberOfTimesTestWillBeRun;
    }
}