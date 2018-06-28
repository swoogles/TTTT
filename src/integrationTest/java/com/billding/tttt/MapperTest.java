package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MapperTest {

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());
        final ChaoticWorld chaoticWorld = new ChaoticWorld();

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            TestEnvironmentParameters::getNumberOfMapperTests,
            (idx) -> new Mapper(
                        new Database(
                            network,
                            componentRunTimes.getDatabase()
                        ),
                        chaoticWorld,
                        componentRunTimes.getMapper()
                    )
        );
    }

    @Test(dataProvider = "mappers")
    public void test_specific(String developer, Mapper mapper) {
        mapper.CRUD_query();
    }
}
