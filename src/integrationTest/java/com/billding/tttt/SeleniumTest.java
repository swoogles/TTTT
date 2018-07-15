package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import com.billding.tttt.external_services.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class SeleniumTest {
    private static final Duration runTime = Duration.ofMillis(20);

    private static class SeleniumTestClass implements UnreliableService {
        private final Application application;
        private final Browser browser;
        private final WebDriver webDriver;
        private final Duration operationRunTime;
        private final ThirdPartyResource javascriptCDN;

        private SeleniumTestClass(Application application, Browser browser, WebDriver webDriver, ThirdPartyResource javascriptCDN, Duration operationRunTime) {
            this.application = application;
            this.browser = browser;
            this.webDriver = webDriver;
            this.javascriptCDN = javascriptCDN;
            this.operationRunTime = operationRunTime;
        }


        @Override
        public Duration getRunTime() {
            return this.operationRunTime;
        }

        @Override
        public Duration failableAction() {
            return this.getRunTime()
                .plus(this.application.failableAction())
                .plus(this.browser.failableAction())
                .plus(this.webDriver.failableAction())
                .plus(this.javascriptCDN.failableAction());
        }
    }



    @DataProvider(name = "seleniumTests")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final Database database = new Database(network, componentRunTimes.getDatabase());
        final Browser browser = new Browser(componentRunTimes.getBrowser());
        final WebDriver webDriver = new WebDriver(runTime);
        final Duration javaScriptCdnOperationRunTime = Duration.ofMillis(20);
        final ThirdPartyResource javascriptCDN =
            new ThirdPartyResource("javascriptCDN", network, javaScriptCdnOperationRunTime);


        Application application = new Application(
            "selenium_test_case",
                new Producer(
                        new KafkaCluster(
                                network,
                                componentRunTimes.getKafkaCluster()
                        ),
                        chaoticWorld, componentRunTimes.getProducer()
                ),
            new AuthService(
                new Intranet(
                    network,
                    componentRunTimes.getIntranet()),
                componentRunTimes.getAuthService()
            ),
            new Controller(
                componentRunTimes.getController(), new Logic(
                chaoticWorld,
                new Mapper(
                    database,
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
        );

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new SeleniumTestClass(application, browser, webDriver, javascriptCDN, runTime));
    }

    @Test(dataProvider = "seleniumTests")
    public void test_simple(String developer, SeleniumTestClass seleniumTestClass) {
        assertTrue(
            seleniumTestClass.failableAction().compareTo(Duration.ofMillis(0)) > 0
        );
    }
}
