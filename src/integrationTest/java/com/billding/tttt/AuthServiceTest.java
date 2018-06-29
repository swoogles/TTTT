package com.billding.tttt;

import com.billding.tttt.external_services.Network;
import org.testng.annotations.Test;

// TODO Consider property for number of test classes to imitate.
public class AuthServiceTest {
    private final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
    private final Network network = new Network(componentRunTimes.getNetwork());
    private final AuthService authService =
        new AuthService(
            new Intranet( network, componentRunTimes.getIntranet()),
            componentRunTimes.getAuthService()
        );

    @Test
    public void test_simple() {
        this.authService.authenticateUser("testUserName", "testPassword");
    }
}
