package com.billding.tttt;

import org.testng.annotations.Test;

/**
 *  Requires:
 *      3rd Party CSS
 *      3rd Party JS
 */
public class SeleniumTest {
    private static final String name = "selenium_test";
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();
    private final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
    private final Network network = new Network(componentRunTimes.getNetwork());

    private final Browser browser = new Browser();
    private final WebDriver webDriver = new WebDriver();


    private final Database database = new Database(network);
    @Test
    public void test_simple() {
        Application application = new Application(
            "selenium_test_case",
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
            new ThirdPartyResource("github", network),
            componentRunTimes.getApplication()
        );
        application.simpleAction();

        /* TODO make an actual SeleniumInstance class and instatiate it here
        final UnreliableServiceImpl test = new UnreliableServiceImpl(
            name,
            false,
            componentRunTimes.getSeleniumTest(),
            application,
            browser,
            webDriver
        );

        test.failableAction();
        */

    }
}
