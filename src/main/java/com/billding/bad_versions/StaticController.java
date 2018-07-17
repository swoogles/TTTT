package com.billding.bad_versions;

import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.AbstractUnreliableService;
import com.billding.tttt.Logic;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Logic}.
 * Since nothing is passed in as a parameter, the Logic, Mapper, & Database must all
 * be correctly configured.
 */
public class StaticController extends AbstractUnreliableService {

    public Duration facilityLevelOperation() {
        return this.failableAction();
    }

    public StaticController() {
        super(null, new ComponentRunTimes("runtimes").getController(), StaticLogic.staticInstance());
    }

}
