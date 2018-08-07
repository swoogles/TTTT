package com.billding.tttt;

import com.billding.meta.*;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;

public class LogicTest {
    private static final World world = DemoScenarios.getWorld();

    @DataProvider(name = "logic")
    public static Object[][] logicInstances() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
                CodeBase::getNumberOfLogicTests,
                (idx) -> new Logic(
                        new Mapper(
                new Database(
                    new Network(componentRunTimes.getNetwork()),
                    componentRunTimes.getDatabase()),
                    world,
                componentRunTimes.getMapper()
            ),
            componentRunTimes.getLogic()
        )
        );
    }

    @Test(dataProvider = "logic")
    public void test_specific(String developer, Logic logic) {
        Duration duration = executeWithRunTime(logic);
        world.do2AssertionsThatNeededToHappenInTheSameMinute(duration);
    }
}
