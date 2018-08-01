package com.billding.tttt;

import com.billding.meta.*;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;
import static org.testng.Assert.assertEquals;

public class ControllerIntegrationTest {
    private final World world = new ChaoticWorld();

    @DataProvider(name = "controllers")
    public static Object[][] primeNumbers() {
        final World world = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfControllerTests,
            (idx) ->
                new Controller(
                    componentRunTimes.getController(),
                    new Logic(
                            world,
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
