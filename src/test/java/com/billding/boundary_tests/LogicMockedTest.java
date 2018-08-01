package com.billding.boundary_tests;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.World;
import com.billding.tttt.Logic;
import com.billding.tttt.Mapper;
import org.junit.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class LogicMockedTest {
    private static final Duration runTime = Duration.ofMillis(5);
    private static final Duration mockedRunTime = Duration.ofMillis(0);
    private final World world = new ChaoticWorld();

    @Test
    public void test_simple() {
        Mapper mapper = mock(Mapper.class);
        when(mapper.failableAction()).thenReturn(mockedRunTime);
        when(mapper.getRunTime()).thenReturn(mockedRunTime);
        final Logic logic = new Logic(
                world,
            mapper,
                runTime
        );
        final Duration result = logic.failableAction();
        assertEquals(result, runTime.plus(mockedRunTime));

        assertEquals(
            logic.failableAction(),
            runTime.plus(mockedRunTime)
        );
    }

}
