package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.Test;

// TODO This needs to use a data provider to run the proper number of tests
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
        logic.failableAction();
    }
}
