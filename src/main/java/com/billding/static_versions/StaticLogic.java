package com.billding.static_versions;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.Logic;
import com.billding.tttt.Mapper;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public final class StaticLogic {
    private static final Logic instance = StaticInstance();

    public static Duration failableAction() {
        return instance.failableAction();
    }
    public static Duration facilityLevelOperation() {
        return instance.facilityLevelOperation();
    }

    static public Duration getRunTime() {
        return instance.getRunTime();
    }

    public static Logic StaticInstance() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        return new Logic(chaoticWorld,
                new Mapper(
                        new Database(
                                network,
                                componentRunTimes.getDatabase()
                        ),
                        chaoticWorld,
                        componentRunTimes.getMapper()
                ),
                componentRunTimes.getLogic()
        );
    }

}
