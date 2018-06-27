package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NetworkMockedTest {
    private static final int networkOperationRunTime = 1;

    @DataProvider(name = "networks")
    public static Object[][] testData() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Network(networkOperationRunTime)
        );

    }

    @Test(dataProvider = "networks")
    public void test_specific(String developer, Network network) {
        assertEquals(networkOperationRunTime, network.httpOperation(200));
        assertEquals(networkOperationRunTime, network.getOperationRunTime());
    }
}
