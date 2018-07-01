package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestEnvironment;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ControllerMockedTest {
    private static final int operationRunTime = 1;

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        final Logic logicMock = mock(Logic.class);

        return testInstanceCreator.createInstances(
            TestEnvironment::getNumberOfControllerTests,
            (idx) -> new Controller(
                operationRunTime,
                logicMock
            ));
    }

    ChaoticWorld chaoticWorld = new ChaoticWorld();

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Controller controller) {
        int numPatients  = 5;
        final int runTimeOfOperationsInBetween = controller.facilityLevelOperation("testFacilityId", numPatients);
        assertEquals(
            controller.getOperationRunTime(),
            operationRunTime
        );
        controller.failableAction();

        // TODO more specific controller action.
//        assertEquals(runTimeOfOperationsInBetween, 10);
    }

    // TOD Should I condense to one test to keep test numbers consistent/dynamic?
    @Test(expectedExceptions = NullPointerException.class)
    public void test_nullResultFromLogic() {
        final Logic logicMock = mock(Logic.class);
        String facilityId = "facilityId";
        int numPatients = 1;
        when (logicMock.facilityLevelOperation(facilityId, numPatients)).thenReturn(null);

        final Controller controller = new Controller(
            operationRunTime,
            logicMock
        );

        controller.facilityLevelOperation(facilityId, numPatients);

    }
}
