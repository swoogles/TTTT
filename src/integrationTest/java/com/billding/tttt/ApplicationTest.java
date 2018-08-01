package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestInstanceCreator;
import com.billding.meta.World;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;

public class ApplicationTest {
    private final World world = new ChaoticWorld();

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final World world = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Application(
                    "test_app" + idx,
                    new Producer(
                            new KafkaCluster(
                                    network,
                                    componentRunTimes.getKafkaCluster()
                            ),
                            world,
                            componentRunTimes.getProducer()
                    ),
                    new AuthService(
                        new Intranet(
                            network,
                            componentRunTimes.getIntranet()),
                        componentRunTimes.getAuthService()
                    ),
                    new Controller(
                        componentRunTimes.getController(),
                       new Logic(
                               world,
                            new Mapper(
                                new Database(network, componentRunTimes.getDatabase()),
                                    world,
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
        executeWithRunTime(application);
    }
}
