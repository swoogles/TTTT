package com.billding.bad_versions;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.World;
import com.billding.tttt.Logic;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.Test;

public class LogicThatUsesNullMapperTest {
    private final World world = new ChaoticWorld();
    private final ComponentRunTimes componentRunTimes =
        new ComponentRunTimes("runtimes");

    @Test
    public void test_simple() {
        final Logic logic = new Logic(
                world,
            new NullMapper(
                new Database(
                    new Network(componentRunTimes.getNetwork()),
                    componentRunTimes.getDatabase()),
                    world,
                componentRunTimes.getMapper()
            ),
            componentRunTimes.getLogic()
        );
        logic.fallibleAction();
    }
}
