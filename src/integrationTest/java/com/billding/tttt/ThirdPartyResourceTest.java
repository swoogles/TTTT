package com.billding.tttt;

import com.billding.meta.*;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;

public class ThirdPartyResourceTest {
    private static final World world = DemoScenarios.getWorld();

    @DataProvider(name = "thirdPartyResources")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new ThirdPartyResource("github", network, componentRunTimes.getThirdPartyResource()));
    }

    @Test(dataProvider = "thirdPartyResources")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        Duration duration = executeWithRunTime(thirdPartyResource);
        world.do2AssertionsThatNeededToHappenInTheSameMinute(duration);
    }
}
