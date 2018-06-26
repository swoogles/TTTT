package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;

public class ControllerMockedTest {

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        final Logic logicMock = mock(Logic.class);

        return testInstanceCreator.createInstances(
            testEnvironmentParameters.getNumberOfControllerTests(),
            (idx) -> new Controller(
                componentRunTimes.getController(),
                logicMock
            ));
    }

    ChaoticWorld chaoticWorld = new ChaoticWorld();

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Controller controller) {
        int numPatients  = 5;
        final int runTimeOfOperationsInBetween = controller.facilityLevelOperation("testFacilityId", numPatients);
        // TODO more specific controller action.
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
