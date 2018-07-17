package com.billding.bad_versions;

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
    private static final Logic instance = staticInstance();

    public static Duration failableAction() {
        return instance.failableAction();
    }

    static public Duration getRunTime() {
        return instance.getRunTime();
    }

    public static Logic staticInstance() {
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
