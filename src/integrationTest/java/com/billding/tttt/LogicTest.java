package com.billding.tttt;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LogicTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();
    private final ComponentRunTimes componentRunTimes = new ComponentRunTimes();

    @Test
    public void test_simple() {
        final Logic logic = new Logic(
            chaoticWorld,
            new Mapper(
                new Database(new Network(componentRunTimes.getNetwork())),
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