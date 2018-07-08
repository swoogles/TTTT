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

    private final Duration operationRunTime;

    public Controller(Duration operationRunTime, Logic logic) {
        this.logic = logic;
        this.operationRunTime = operationRunTime;
    }

    // Is the possibility of a null result from this.logic.facilityLevelOperation the last remaining bit to cover?
    public Duration facilityLevelOperation() {
        return this.failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime
            .plus(this.logic.getOperationRunTime());
    }

    @Override
    public Duration failableAction() {
        this.logic.failableAction();
        return this.getOperationRunTime();
    }
}
