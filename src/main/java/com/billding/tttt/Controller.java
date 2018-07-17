package com.billding.tttt;

// TODO add terrible Struts 1 dependency
// Consider renaming to Resource/RESTEndpoint/etc.

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Logic}.
 */
public class Controller extends AbstractUnreliableService {
    private static final String name = "controller";

    public Controller(Duration runTime, Logic logic) {
        super(null, runTime, logic);
    }

}
