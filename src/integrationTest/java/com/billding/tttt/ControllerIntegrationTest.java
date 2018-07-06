package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ControllerIntegrationTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "controllers")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) ->
                new Controller(
                    componentRunTimes.getController(),
                    new Logic(
                        chaoticWorld,
                        new Mapper(
                            new Database(
                                network,
                                componentRunTimes.getDatabase()
                            ),
                            chaoticWorld,
                            componentRunTimes.getMapper()
                        ),
                        componentRunTimes.getLogic()
                    )
                ));
    }

    @Test(dataProvider = "controllers")
    public void test_simple(String developer, Controller controller) {
        int numPatients  = 5;
        assertEquals(
            controller.facilityLevelOperation("testFacilityId", numPatients),
            Duration.ofMillis(200)
        );
        Duration runTimeOfOperationsInBetween = controller.failableAction();
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
