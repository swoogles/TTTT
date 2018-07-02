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

import static org.testng.Assert.assertTrue;

public class SeleniumTest {
    private static final int operationRunTime = 20;

    private static class SeleniumTestClass implements UnreliableService {
        private final Application application;
        private final Browser browser;
        private final WebDriver webDriver;
        private final int operationRunTime;
        private final ThirdPartyResource javascriptCDN;

        private SeleniumTestClass(Application application, Browser browser, WebDriver webDriver, ThirdPartyResource javascriptCDN, int operationRunTime) {
            this.application = application;
            this.browser = browser;
            this.webDriver = webDriver;
            this.javascriptCDN = javascriptCDN;
            this.operationRunTime = operationRunTime;
        }


        @Override
        public int getOperationRunTime() {
            return this.operationRunTime;
        }

        @Override
        public int failableAction() {
            return this.getOperationRunTime()
                + this.application.failableAction()
                + this.browser.failableAction()
                + this.webDriver.failableAction()
                + this.javascriptCDN.failableAction();
        }
    }



    @DataProvider(name = "seleniumTests")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final Database database = new Database(network, componentRunTimes.getDatabase());
        final Browser browser = new Browser(componentRunTimes.getBrowser());
        final WebDriver webDriver = new WebDriver(operationRunTime);
        final int javaScriptCdnOperationRunTime = 20;
        final ThirdPartyResource javascriptCDN =
            new ThirdPartyResource("javascriptCDN", network, javaScriptCdnOperationRunTime);


        Application application = new Application(
            "selenium_test_case",
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
            (idx) -> new SeleniumTestClass(application, browser, webDriver, javascriptCDN, operationRunTime));
    }

    @Test(dataProvider = "seleniumTests")
    public void test_simple(String developer, SeleniumTestClass seleniumTestClass) {
        assertTrue(
            seleniumTestClass.failableAction() > 0
        );
    }
}
