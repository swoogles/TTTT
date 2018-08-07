package com.billding.bad_versions;

import com.billding.meta.ComponentRunTimes;
import com.billding.meta.DemoScenarios;
import com.billding.meta.World;
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
        return instance.fallibleAction();
    }

    static public Duration getRunTime() {
        return instance.getRunTime();
    }

    public static Logic staticInstance() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());
        final World world = DemoScenarios.getWorld();
        return new Logic(
                new Mapper(
                        new Database(
                                network,
                                componentRunTimes.getDatabase()
                        ),
                        world,
                        componentRunTimes.getMapper()
                ),
                componentRunTimes.getLogic()
        );
    }
}
