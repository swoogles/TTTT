package com.billding.nonpresentation;

import com.billding.meta.CodeBase;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class DatabaseMockedTest {
    private static final Duration operationRunTime = Duration.ofMillis(10);
    private static final Duration mockedOperationRuntime = Duration.ofMillis(0);

    @DataProvider(name = "databases")
    public static Object[][] primeNumbers() {
        final Network network = mock(Network.class);
        when(network.failableAction()).thenReturn(mockedOperationRuntime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfMapperTests,
            (idx) -> new Database( network, operationRunTime)
        );
    }

    @Test(dataProvider = "databases")
    public void test_specific(String developer, Database database) {
        assertEquals(operationRunTime, database.failableAction());
    }
}
