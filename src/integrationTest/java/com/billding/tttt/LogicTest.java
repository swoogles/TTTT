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

// TODO This needs to use a data provider to run the proper number of tests
public class LogicTest {

    @DataProvider(name = "logic")
    public static Object[][] logicInstances() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final ChaoticWorld chaoticWorld = new ChaoticWorld();

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
                CodeBase::getNumberOfLogicTests,
                (idx) -> new Logic(
            chaoticWorld,
            new Mapper(
                new Database(
                    new Network(componentRunTimes.getNetwork()),
                    componentRunTimes.getDatabase()),
                chaoticWorld,
                componentRunTimes.getMapper()
            ),
            componentRunTimes.getLogic()
        )
        );
    }

    @Test(dataProvider = "logic")
    public void test_specific(String developer, Logic logic) {
        executeWithRunTime(logic);
    }
}
