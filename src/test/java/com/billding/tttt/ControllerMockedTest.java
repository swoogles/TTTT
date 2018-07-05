package com.billding.tttt;

import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ControllerMockedTest {
    private static final int operationRunTime = 1;

    private static final int numPatients  = 5;
    private static final String facilityId = "test_facility_id";

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        final Logic logicMock = mock(Logic.class);
        when (logicMock.facilityLevelOperation(facilityId, numPatients)).thenReturn(Collections.singletonList(operationRunTime));

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfControllerTests,
            (idx) -> new Controller(
                operationRunTime,
                logicMock
            ));
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Controller controller) {

        assertEquals(
        controller.facilityLevelOperation(facilityId, numPatients),
            operationRunTime
        );
        assertEquals(
            controller.getOperationRunTime(),
            operationRunTime
        );
        controller.failableAction();
    }

}
