package com.billding.tttt;

import com.billding.meta.*;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;

public class ControllerIntegrationTest {
    private static final World world = DemoScenarios.getWorld();

    @DataProvider(name = "controllers")
    public static Object[][] controllers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfControllerTests,
            (idx) ->
                new Controller(
                    componentRunTimes.getController(),
                    new Logic(
                            new Mapper(
                            new Database(
                                network,
                                componentRunTimes.getDatabase()
                            ),
                                world,
                            componentRunTimes.getMapper()
                        ),
                        componentRunTimes.getLogic()
                    )
                ));
    }

    @Test(dataProvider = "controllers")
    public void test_simple(String developer, Controller controller) {
        Duration runTimeOfOperationsInBetween = executeWithRunTime(controller);
        world.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
