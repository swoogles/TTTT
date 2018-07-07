package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.Database;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class MapperMockedTest {
    private static final Duration mapperOperationRunTime = Duration.ofMillis(1);
    private static final Duration mockedOperationRuntime = Duration.ofMillis(0);

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = mock(ChaoticWorld.class);
        when(chaoticWorld.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        final Database database = mock(Database.class);
        when(database.failableAction()).thenReturn(mockedOperationRuntime);
        when(database.getOperationRunTime()).thenReturn(mockedOperationRuntime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfMapperTests,
            (idx) -> new Mapper(
                        database,
                        chaoticWorld,
                        mapperOperationRunTime
                    )
        );
    }

    @Test(dataProvider = "mappers")
    public void test_specific(String developer, Mapper mapper) {
        assertEquals(mapperOperationRunTime, mapper.CRUD_query());
    }
}
