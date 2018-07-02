package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class LogicMockedTest {
    private static final int operationRuntime = 5;
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @Test
    public void test_simple() {
        Mapper mapper = mock(Mapper.class);
        when(mapper.CRUD_query()).thenReturn(5);
        int mapperOperationRuntime = 1;
        when(mapper.failableAction()).thenReturn(mapperOperationRuntime);
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
            assertEquals(facilityId.get(i), Integer.valueOf(5));
        }

        assertEquals(
            logic.failableAction(),
            operationRuntime + mapperOperationRuntime
        );
    }
}
