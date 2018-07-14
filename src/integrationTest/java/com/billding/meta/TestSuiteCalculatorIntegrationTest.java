package com.billding.meta;

import com.billding.tttt.*;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.Test;

public class TestSuiteCalculatorIntegrationTest {
    final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
    public void setup() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final Network network = new Network(componentRunTimes.getNetwork());


        Database database = new Database(network, componentRunTimes.getDatabase());
        Mapper mapper = new Mapper(
                database,
                chaoticWorld,
                componentRunTimes.getMapper()
        );

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        testInstanceCreator.createInstances(
                (ignored) -> 1,
                (idx) -> new Application(
                        "test_app" + idx,
                        new KafkaCluster(
                                network,
                                componentRunTimes.getKafkaCluster()
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
                                        chaoticWorld,
                                        mapper,
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

    @Test
    public void test_basic() {
        final TestEnvironment testEnvironment = new TestEnvironment("solo_project");
        final CodeBase codeBase = new CodeBase("minimal");
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                testEnvironment,
                componentRunTimes,
                codeBase
        );
        System.out.println(
                "Runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
    }
}
