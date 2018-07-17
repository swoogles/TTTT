package com.billding.bad_versions;

import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.Logic;
import com.billding.tttt.UnreliableService;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Logic}.
 * Since nothing is passed in as a parameter, the Logic, Mapper, & Database must all
 * be correctly configured.
 */
public class StaticController implements UnreliableService {
    private final static ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
    private final Duration runTime = componentRunTimes.getController();

    public Duration facilityLevelOperation() {
        return this.failableAction();
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(StaticLogic.getRunTime());
    }

    @Override
    public Duration failableAction() {
        StaticLogic.failableAction();
        return this.getRunTime();
    }
}
