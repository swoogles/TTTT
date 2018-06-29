package com.billding.nonpresentation;

import com.billding.tttt.Intranet;
import com.billding.tttt.TestInstanceCreator;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

public class IntranetMockedTest {
    private static final int operationRunTime = 10;

    @DataProvider(name = "networks")
    public static Object[][] testData() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Intranet(mock(Network.class), operationRunTime)
        );

    }

    @Test(dataProvider = "networks")
    public void test_specific(String developer, Intranet intranet) {
        assertEquals(operationRunTime, intranet.failableAction());
    }
}
