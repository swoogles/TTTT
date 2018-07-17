package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;

import java.time.Duration;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class Logic extends AbstractUnreliableService {
    private final ChaoticWorld chaoticWorld;

    public Logic(ChaoticWorld chaoticWorld, Mapper mapper, Duration runTime) {
        super(null, runTime, mapper);
        this.chaoticWorld = chaoticWorld;
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
