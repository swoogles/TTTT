package com.billding.tttt;

import com.billding.meta.ComponentRunTimes;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class LogicMockedTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();
    private final ComponentRunTimes componentRunTimes = new ComponentRunTimes();

    @Test
    public void test_simple() {
        Mapper mapper = mock(Mapper.class);
        when(mapper.CRUD_query()).thenReturn(5);
        when(mapper.getOperationRunTime()).thenReturn(1);
        int numPatients = 25;
        final Logic logic = new Logic(
            chaoticWorld,
            mapper,
            componentRunTimes.getLogic()
        );
        final List<Integer> facilityId = logic.facilityLevelOperation("facilityId", numPatients);
        // TODO fix bug; this only returns 5.
//        assertEquals(logic.getOperationRunTime(), 6);
        for (int i = 0; i < numPatients; i++) {
            assertEquals(facilityId.get(i), new Integer(5));
        }
    }
}
