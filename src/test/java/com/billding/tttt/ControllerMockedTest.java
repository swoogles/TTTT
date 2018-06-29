package com.billding.tttt;

import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestEnvironment;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class ControllerMockedTest {

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        final Logic logicMock = mock(Logic.class);

        return testInstanceCreator.createInstances(
            TestEnvironment::getNumberOfControllerTests,
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
