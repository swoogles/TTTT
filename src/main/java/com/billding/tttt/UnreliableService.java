package com.billding.tttt;


import java.time.Duration;

/**
 * The *MOST* important abstraction in the whole project.
 * Every important piece that I'm trying to demonstrate involves unreliable operations.
 */
public interface UnreliableService {
    /**
     * A way of getting the cost of an operation, without actually executing.
     * This avoids possible failures
     * @return Runtime of an operation on the service.
     */
    Duration getRunTime();

    // TODO Fix spelling

    /**
     * This is where the error-prone operations that initiated this whole project occur.
     * We allow for an unlimited creation of new Services, that could all fail for completely
     * distinct reasons.
     *
     * @return Time taken to perform action
     */
    Duration failableAction();
}
