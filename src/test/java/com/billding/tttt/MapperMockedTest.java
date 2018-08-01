package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import com.billding.meta.World;
import com.billding.tttt.external_services.Database;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class MapperMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final World world = mock(ChaoticWorld.class);
        when(world.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        final Database database = mock(Database.class);
        when(database.failableAction()).thenReturn(mockedRunTime);
        when(database.getRunTime()).thenReturn(mockedRunTime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfMapperTests,
            (idx) -> new Mapper(
                        database,
                    world,
                    runTime
                    )
        );
    }

    @Test(dataProvider = "mappers")
    public void test_specific(String developer, Mapper mapper) {
        assertEquals(runTime, mapper.failableAction());
    }
}
