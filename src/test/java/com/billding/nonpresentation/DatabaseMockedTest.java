package com.billding.nonpresentation;

import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import com.billding.meta.TestEnvironment;
import com.billding.tttt.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class DatabaseMockedTest {
    // TODO Make a constructor arg of Database
    private static final int operationRunTime = 10;

    @DataProvider(name = "databases")
    public static Object[][] primeNumbers() {
        final Network network = mock(Network.class);
        when(network.failableAction()).thenReturn(0);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            TestEnvironment::getNumberOfMapperTests,
            (idx) -> new Database( network, operationRunTime)
        );
    }

    @Test(dataProvider = "databases")
    public void test_specific(String developer, Database database) {
        assertEquals(operationRunTime, database.failableAction());
    }
}
