package com.billding.bad_versions;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.Logic;
import com.billding.tttt.Mapper;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.Test;

public class LogicThatUsesNullMapperTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();
    private final ComponentRunTimes componentRunTimes =
        new ComponentRunTimes("runtimes");

    @Test
    public void test_simple() {
        final Logic logic = new Logic(
            chaoticWorld,
            new NullMapper(
                new Database(
                    new Network(componentRunTimes.getNetwork()),
                    componentRunTimes.getDatabase()),
                chaoticWorld,
                componentRunTimes.getMapper()
            ),
            componentRunTimes.getLogic()
        );
        logic.failableAction();
    }
}
