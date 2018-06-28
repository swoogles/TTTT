package com.billding.tttt;

import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ThirdPartyResourceTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "thirdPartyResources")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new ThirdPartyResource("github", network, componentRunTimes.getThirdPartyResource()));
    }

    @Test(dataProvider = "thirdPartyResources")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        int runTimeOfOperationsInBetween = thirdPartyResource.failableAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
