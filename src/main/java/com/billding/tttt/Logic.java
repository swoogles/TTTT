package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class Logic implements UnreliableService {
    private final ChaoticWorld chaoticWorld;
    private final Mapper mapper;
    private static final String name = "logic";

    private final Duration runTime;

    public Logic(ChaoticWorld chaoticWorld, Mapper mapper, Duration runTime) {
        this.chaoticWorld = chaoticWorld;
        this.mapper = mapper;
        this.runTime = runTime;
    }

    public Duration facilityLevelOperation() {
        return this.failableAction();
    }

    @Override
    public Duration getRunTime() {
        return this.runTime
            .plus(this.mapper.getRunTime());
    }

    @Override
    public Duration failableAction() {
        return
            this.getRunTime()
            .plus(this.mapper.failableAction());
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
