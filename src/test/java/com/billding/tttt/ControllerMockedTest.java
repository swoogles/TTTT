package com.billding.tttt;

import com.billding.meta.TestEnvironment;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
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

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Controller controller) {
        int numPatients  = 5;
        /* TODO Determine how to fiddle with this for a non-0 result
        final Logic logicMock = mock(Logic.class);
        when (logicMock.facilityLevelOperation(facilityId, numPatients)).thenReturn(Collections.emptyList());
        */

        assertEquals(
        controller.facilityLevelOperation("testFacilityId", numPatients),
            0
        );
        assertEquals(
            controller.getOperationRunTime(),
            operationRunTime
        );
        controller.failableAction();
    }
}
