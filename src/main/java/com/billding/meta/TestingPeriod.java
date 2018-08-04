package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

import java.time.Period;

public class TestingPeriod {
    private final int developerHours;
    private final int funkyDaysNumber;

    public TestingPeriod(String resourceName) {
        System.out.println("doing stuff");
        PropertyRetriever propertyRetriever = new PropertyRetriever("testing_periods/" + resourceName);
        Period timeWindow = Period.parse(
                propertyRetriever.getString("time_window")

        );
        int monthDays = (int) timeWindow.toTotalMonths() * 30;
        int days = timeWindow.getDays();
        this.funkyDaysNumber = monthDays + days;
        // TODO Improve super janky day calculation
        int numberOfHours = propertyRetriever.getInt("hours_in_work_day");
        int runsPerDeveloperPerHour = propertyRetriever.getInt("runs_per_developer_per_hour");
        this.developerHours = runsPerDeveloperPerHour * numberOfHours;
    }

    public int getDeveloperHours() {
        return this.developerHours;
    }

    public int getFunkyDaysNumber() {
        return this.funkyDaysNumber;

    }
}
