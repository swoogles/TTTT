package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class DatabaseMockedTest {
    // TODO Make a constructor arg of Database
    private static final int mapperOperationRunTime = 10;

    @DataProvider(name = "databases")
    public static Object[][] primeNumbers() {
        final Network network = mock(Network.class);
        when(network.failableAction()).thenReturn(0);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            TestEnvironmentParameters::getNumberOfMapperTests,
            (idx) -> new Database(
                        network
                    )
        );
    }

    @Test(dataProvider = "databases")
    public void test_specific(String developer, Database database) {
        assertEquals(mapperOperationRunTime, database.failableAction());
        assertEquals(mapperOperationRunTime, database.getOperationRunTime());
    }
}
