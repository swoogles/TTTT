package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.testng.Assert.assertTrue;

public class ControllerIntegrationTest {

    @DataProvider(name = "controllers")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());
        final Database database = new Database(network);

        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();

        final Object[][] controllers =
            IntStream.range(0, testEnvironmentParameters.getNumberOfControllerTests()).mapToObj(idx -> new Object[]{
                testEnvironmentParameters.getRandomDeveloper(),
                    new Controller(
                        componentRunTimes.getController(),
                       new Logic(
                            chaoticWorld,
                            new Mapper(
                                database,
                                chaoticWorld,
                                componentRunTimes.getMapper()
                            ),
                            componentRunTimes.getLogic()
                        )
                    ),
            }).toArray(size -> new Object[size][1]);
        return controllers;
    }

    ChaoticWorld chaoticWorld = new ChaoticWorld();

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
