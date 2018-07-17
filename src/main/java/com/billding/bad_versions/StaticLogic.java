package com.billding.bad_versions;

import com.billding.tttt.Logic;
import com.billding.tttt.Mapper;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public final class StaticLogic {
    private static final Logic instance = Logic.staticInstance();

    public static Duration failableAction() {
        return instance.failableAction();
    }
    public static Duration facilityLevelOperation() {
        return instance.facilityLevelOperation();
    }

    static public Duration getRunTime() {
        return instance.getRunTime();
    }

}
