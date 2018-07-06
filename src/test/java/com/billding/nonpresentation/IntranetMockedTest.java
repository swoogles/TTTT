package com.billding.nonpresentation;

import com.billding.tttt.Intranet;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class IntranetMockedTest {
    private static final Duration operationRunTime = Duration.ofMillis(10);
    private static final Duration mockedOperationRuntime = Duration.ofMillis(0);

    @DataProvider(name = "networks")
    public static Object[][] testData() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();
        final Network network = mock(Network.class);
        when(network.failableAction()).thenReturn(mockedOperationRuntime);

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Intranet(network, operationRunTime)
        );

    }

    @Test(dataProvider = "networks")
    public void test_specific(String developer, Intranet intranet) {
        assertEquals(operationRunTime, intranet.failableAction());
    }
}
