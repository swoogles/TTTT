package com.billding.bad_versions;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import com.billding.meta.World;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class NullMapperTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final World world = mock(ChaoticWorld.class);
        when(world.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        Network network = mock(Network.class);
        when(network.getRunTime()).thenReturn(mockedRunTime);
        when(network.fallibleAction()).thenReturn(mockedRunTime);


        Database database = new Database(
                network,
                mockedRunTime
        );

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
                CodeBase::getNumberOfMapperTests,
                (idx) -> new NullMapper(
                        database,
                        world,
                        runTime
                )
        );
    }

    @Test(dataProvider = "mappers")
    public void test_specific(String developer, NullMapper mapper) {
        assertEquals(mapper.CRUD_query(), runTime );
        assertEquals(mapper.getRunTime(), runTime );
    }

    @Test
    public void test_forcedFailure() {
        final World world = mock(ChaoticWorld.class);
        when(world.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        Network network = mock(Network.class);
        when(network.getRunTime()).thenReturn(mockedRunTime);
        when(network.fallibleAction()).thenThrow(new RuntimeException("Forced failure for code coverage"));


        Database database = new Database(
                network,
                mockedRunTime
        );
        NullMapper mapper = new NullMapper(
                database,
                world,
                runTime
        );
        mapper.fallibleAction();
    }
}
