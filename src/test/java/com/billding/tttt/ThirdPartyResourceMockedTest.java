package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ThirdPartyResourceMockedTest {
    private static final int operationRunTime = 1;
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "thirdPartyResources")
    public static Object[][] primeNumbers() {
        final Network network = mock(Network.class);
        when(network.httpOperation(200)).thenReturn(0);

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> operationRunTime,
            (idx) -> new ThirdPartyResource("github", network, operationRunTime));
    }

    @Test(dataProvider = "thirdPartyResources")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        assertEquals(
            thirdPartyResource.failableAction(),
            operationRunTime
        );
    }
}
