package com.billding.boundary_tests;

import com.billding.meta.*;
import com.billding.tttt.Mapper;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class MapperMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);

    @DataProvider(name = "mappers")
    public static Object[][] mappers() {
        final World world = DemoScenarios.getPlatonicWorld();

        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Database database =
                new Database(new Network(componentRunTimes.getNetwork()), componentRunTimes.getMapper());

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
        assertTrue(mapper.fallibleAction().compareTo(runTime) > 0  );
    }
}
