package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class LogicMockedTest {
    private static final Duration operationRuntime = Duration.ofMillis(5);
    private static final Duration mockedOperationRuntime = Duration.ofMillis(0);
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @Test
    public void test_simple() {
        Mapper mapper = mock(Mapper.class);
        when(mapper.CRUD_query()).thenReturn(mockedOperationRuntime);
        when(mapper.failableAction()).thenReturn(mockedOperationRuntime);
        when(mapper.getOperationRunTime()).thenReturn(mockedOperationRuntime);
        int numPatients = 25;
        final Logic logic = new Logic(
            chaoticWorld,
            mapper,
            operationRuntime
        );
        final Duration result = logic.facilityLevelOperation();
        assertEquals(result, operationRuntime.plus(mockedOperationRuntime));

        assertEquals(
            logic.failableAction(),
            operationRuntime.plus(mockedOperationRuntime)
        );
    }
}
