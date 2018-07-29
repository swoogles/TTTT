package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.CodeBase;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;

public class MapperTest {

    @DataProvider(name = "mappers")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());
        final ChaoticWorld chaoticWorld = new ChaoticWorld();

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfMapperTests,
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
        executeWithRunTime(mapper);
    }
}
