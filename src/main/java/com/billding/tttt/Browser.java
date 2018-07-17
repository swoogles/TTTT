package com.billding.tttt;

import java.time.Duration;

/**
 * This is a bit of software that must be installed on the machine before Selenium tests can execute.
 */
public class Browser extends AbstractUnreliableService{
    // TODO Consider taking different browser names as parameter
    private static final String name = "browser";

    public Browser(Duration runTime) {
        super(name, runTime);
    }

}
