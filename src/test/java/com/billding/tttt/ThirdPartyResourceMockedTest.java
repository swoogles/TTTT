package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ThirdPartyResourceMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "thirdPartyResources")
    public static Object[][] primeNumbers() {
        final Network network = mock(Network.class);
        when(network.httpOperation(200)).thenReturn(mockedRunTime);
        when(network.getRunTime()).thenReturn(mockedRunTime);

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new ThirdPartyResource("github", network, runTime));
    }

    @Test(dataProvider = "thirdPartyResources")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        assertEquals(
            thirdPartyResource.failableAction(),
                runTime
        );
        assertEquals(
            thirdPartyResource.failableAction(),
                runTime
        );
    }
}
