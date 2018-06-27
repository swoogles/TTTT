package com.billding.tttt;

import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class AuthServiceMockedTest {
    private final int authServiceRunTime = 1;
    private final Intranet mockIntraNet = mock(Intranet.class);
    private final AuthService authService =
        new AuthService(
            mockIntraNet,
            authServiceRunTime
        );

    public AuthServiceMockedTest() {
        when(mockIntraNet.failableAction()).thenReturn(0);
    }

    @Test
    public void test_simple() {
        assertEquals(authServiceRunTime, this.authService.authenticateUser("testUserName", "testPassword"));
    }
}
