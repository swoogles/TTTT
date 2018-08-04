package com.billding.boundary_tests;

import com.billding.meta.DemoScenarios;
import com.billding.meta.TestInstanceCreator;
import com.billding.meta.World;
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
    // TODO This should be a parameter to the Network & to the Resource.
    private static final World world = DemoScenarios.getPlatonicWorld();

    @DataProvider(name = "thirdPartyResources")
    public static Object[][] primeNumbers() {
        final Network network = mock(Network.class);
        when(network.fallibleAction()).thenReturn(mockedRunTime);
        when(network.getRunTime()).thenReturn(mockedRunTime);

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new ThirdPartyResource("github", network, runTime));
    }

    @Test(dataProvider = "thirdPartyResources")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        assertEquals(
            thirdPartyResource.fallibleAction(),
                runTime
        );
        assertEquals(
            thirdPartyResource.fallibleAction(),
                runTime
        );
    }
}
