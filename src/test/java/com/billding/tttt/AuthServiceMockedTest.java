package com.billding.tttt;

import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class AuthServiceMockedTest {
    private static final Duration authServiceRunTime = Duration.ofMillis(1);
    private static final Duration mockedOperationRuntime = Duration.ofMillis(0);
    private final Intranet mockIntraNet = mock(Intranet.class);
    private final AuthService authService =
        new AuthService(
            mockIntraNet,
            authServiceRunTime
        );

    public AuthServiceMockedTest() {
        when(mockIntraNet.failableAction()).thenReturn(mockedOperationRuntime);
    }

    @Test
    public void test_simple() {
        assertEquals(authServiceRunTime, this.authService.authenticateUser("testUserName", "testPassword"));
    }
}
