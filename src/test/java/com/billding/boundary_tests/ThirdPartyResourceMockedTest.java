package com.billding.boundary_tests;

import com.billding.meta.CodeBase;
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

    @DataProvider(name = "thirdPartyResources")
    public static Object[][] thirdPartyResources() {
        final Network network = mock(Network.class);
        when(network.fallibleAction()).thenReturn(mockedRunTime);
        when(network.getRunTime()).thenReturn(mockedRunTime);

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfThirdPartyResourceTests,
            (idx) -> new ThirdPartyResource("github", network, runTime));
    }

    @Test(dataProvider = "thirdPartyResources")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        assertEquals(
            thirdPartyResource.fallibleAction(),
                runTime
        );
    }
}
