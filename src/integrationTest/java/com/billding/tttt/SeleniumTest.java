package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.SeleniumTestClass;
import com.billding.meta.World;
import com.billding.tttt.*;
import com.billding.tttt.external_services.Database;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import com.billding.tttt.external_services.ThirdPartyResource;
import com.billding.tttt.external_services.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;
import static org.testng.Assert.assertTrue;

public class SeleniumTest {
    private static final Duration runTime = Duration.ofMillis(20);


    /* Commented so I just have a single Selenium test for presenting

    @DataProvider(name = "seleniumTests")
    public static Object[][] primeNumbers() {
        final World world = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final Database database = new Database(network, componentRunTimes.getDatabase());
        final Browser browser = new Browser(componentRunTimes.getBrowser());
        final WebDriver webDriver = new WebDriver(runTime);
        final Duration javaScriptCdnOperationRunTime = Duration.ofMillis(20);
        final ThirdPartyResource javascriptCDN =
            new ThirdPartyResource("javascript_cdn", network, javaScriptCdnOperationRunTime);


        Application application = new Application(
            "selenium_test_case",
                new Producer(
                        new KafkaCluster(
                                network,
                                componentRunTimes.getKafkaCluster()
                        ),
                        world, componentRunTimes.getProducer()
                ),
            new AuthService(
                new Intranet(
                    network,
                    componentRunTimes.getIntranet()),
                componentRunTimes.getAuthService()
            ),
            new Controller(
                componentRunTimes.getController(), new Logic(
                    new Mapper(
                    database,
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
        );

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfSeleniumTests,
            (idx) -> new SeleniumTestClass(application, browser, webDriver, javascriptCDN, runTime));
    }

    @Test(dataProvider = "seleniumTests")
    public void test_simple(String developer, SeleniumTestClass seleniumTestClass) {
        executeWithRunTime(
                seleniumTestClass
        );
    }
     */

    @Test
    public void simpleTest() {
        final World world = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");
        final Network network = new Network(componentRunTimes.getNetwork());

        final Database database = new Database(network, componentRunTimes.getDatabase());
        final Browser browser = new Browser(componentRunTimes.getBrowser());
        final WebDriver webDriver = new WebDriver(runTime);
        final Duration javaScriptCdnOperationRunTime = Duration.ofMillis(20);
        final ThirdPartyResource javascriptCDN =
                new ThirdPartyResource("javascript_cdn", network, javaScriptCdnOperationRunTime);


        Application application = new Application(
                "selenium_test_case",
                new Producer(
                        new KafkaCluster(
                                network,
                                componentRunTimes.getKafkaCluster()
                        ),
                        world, componentRunTimes.getProducer()
                ),
                new AuthService(
                        new Intranet(
                                network,
                                componentRunTimes.getIntranet()),
                        componentRunTimes.getAuthService()
                ),
                new Controller(
                        componentRunTimes.getController(), new Logic(
                        new Mapper(
                                database,
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
        );
        SeleniumTestClass seleniumTestClass = new SeleniumTestClass(application, browser, webDriver, javascriptCDN, runTime);
        seleniumTestClass.fallibleAction();
    }
}
