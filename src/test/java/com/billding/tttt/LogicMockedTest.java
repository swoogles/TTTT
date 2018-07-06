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
        int numPatients = 25;
        final Logic logic = new Logic(
            chaoticWorld,
            mapper,
            operationRuntime
        );
        final List<Integer> facilityId = logic.facilityLevelOperation("facilityId", numPatients);
        // TODO fix bug; this only returns 5.
//        assertEquals(logic.getOperationRunTime(), 6);
        for (int i = 0; i < numPatients; i++) {
            assertEquals(facilityId.get(i), Integer.valueOf(0));
        }

        assertEquals(
            logic.failableAction(),
            operationRuntime.plus(mockedOperationRuntime)
        );
    }
}
