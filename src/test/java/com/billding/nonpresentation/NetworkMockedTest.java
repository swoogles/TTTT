package com.billding.nonpresentation;

import com.billding.tttt.external_services.Network;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class NetworkMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);

    @DataProvider(name = "networks")
    public static Object[][] testData() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Network(runTime)
        );

    }

    @Test(dataProvider = "networks")
    public void test_specific(String developer, Network network) {
        assertEquals(runTime, network.httpOperation(200));
    }
}
