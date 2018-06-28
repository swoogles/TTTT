package com.billding.tttt;

import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ApplicationTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Application(
                    "test_app" + idx,
                    new KafkaCluster(
                        network
                    ),
                    new AuthService(
                        new Intranet(
                            network
                        ),
                        componentRunTimes.getAuthService()
                    ),
                    new Controller(
                        componentRunTimes.getController(),
                       new Logic(
                            chaoticWorld,
                            new Mapper(
                                new Database(network, componentRunTimes.getDatabase()),
                                chaoticWorld,
                                componentRunTimes.getMapper()
                            ),
                            componentRunTimes.getLogic()
                        )
                    ),
                    new ThirdPartyResource(
                        "github",
                        network,
                        componentRunTimes.getThirdPartyResource()
                    ),
                    componentRunTimes.getApplication()
                ));
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Application application) {
        int runTimeOfOperationsInBetween = application.failableAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
