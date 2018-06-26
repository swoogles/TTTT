package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class MapperTest {

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final Database database = new Database(network);

        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();

        final Object[][] applications =
            IntStream.range(0, testEnvironmentParameters.getNumberOfMapperTests())
                .mapToObj(idx -> new Object[]{
                    testEnvironmentParameters.getRandomDeveloper(),
                    new Mapper(
                        database,
                        chaoticWorld,
                        componentRunTimes.getMapper()
                    )
                }).toArray(size -> new Object[size][1]);
        return applications;
    }

    @Test(dataProvider = "mappers")
    public void test_specific(String developer, Mapper mapper) {
        mapper.CRUD_query();
    }
}
