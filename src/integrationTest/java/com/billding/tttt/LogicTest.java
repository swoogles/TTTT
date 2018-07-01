package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.Test;

public class LogicTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();
    private final ComponentRunTimes componentRunTimes =
        new ComponentRunTimes("runtimes");

    @Test
    public void test_simple() {
        final Logic logic = new Logic(
            chaoticWorld,
            new Mapper(
                new Database(
                    new Network(componentRunTimes.getNetwork()),
                    componentRunTimes.getDatabase()),
                chaoticWorld,
                componentRunTimes.getMapper()
            ),
            componentRunTimes.getLogic()
        );
        logic.facilityLevelOperation("facilityId", 25);
        // TODO Use some kind of "ReasonableExpectation" class/enum to gauge runtime costs
//        assertEquals(logic.getOperationRunTime(), 45);
    }
}
