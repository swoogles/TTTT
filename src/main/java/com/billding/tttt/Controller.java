package com.billding.tttt;

// TODO add terrible Struts 1 dependency
// Consider renaming to Resource/RESTEndpoint/etc.

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Logic}.
 */
public class Controller implements UnreliableService {
    private final Logic logic;
    private static final String name = "controller";

    private final Duration runTime;

    public Controller(Duration runTime, Logic logic) {
        this.logic = logic;
        this.runTime = runTime;
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(this.logic.getRunTime());
    }

    @Override
    public Duration failableAction() {
        this.logic.failableAction();
        return this.getRunTime();
    }
}
