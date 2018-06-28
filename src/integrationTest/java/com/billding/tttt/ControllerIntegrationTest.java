package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ControllerIntegrationTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "controllers")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
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
        final int results = controller.facilityLevelOperation("testFacilityId", numPatients);
        // TODO more specific controller action.
//        assertEquals(runTimeOfOperationsInBetween, 10);
        int runTimeOfOperationsInBetween = controller.failableAction();
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
