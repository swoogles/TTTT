package com.billding.boundary_tests;

import com.billding.tttt.AuthService;
import com.billding.tttt.Intranet;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class AuthServiceMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);
    private final Intranet mockIntraNet = mock(Intranet.class);
    private final AuthService authService =
        new AuthService(
            mockIntraNet,
                runTime
        );

    public AuthServiceMockedTest() {
        when(mockIntraNet.fallibleAction()).thenReturn(mockedRunTime);
        when(mockIntraNet.getRunTime()).thenReturn(mockedRunTime);
    }

    @Test
    public void test_simple() {
        assertEquals(runTime, this.authService.fallibleAction());
    }
}
