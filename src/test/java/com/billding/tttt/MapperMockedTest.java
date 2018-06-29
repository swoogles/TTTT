package com.billding.tttt;

import com.billding.meta.TestEnvironment;
import com.billding.tttt.external_services.Database;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class MapperMockedTest {
    private static final int mapperOperationRunTime = 1;

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = mock(ChaoticWorld.class);
        when(chaoticWorld.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        final Database database = mock(Database.class);
        when(database.failableAction()).thenReturn(0);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            TestEnvironment::getNumberOfMapperTests,
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
