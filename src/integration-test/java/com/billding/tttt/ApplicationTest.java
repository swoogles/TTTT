package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class ApplicationTest {

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());
        final Database database = new Database(network);

        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();

        final Object[][] applications =
            IntStream.range(0, testEnvironmentParameters.getNumberOfApplicationTests()).mapToObj(idx -> new Object[]{
                testEnvironmentParameters.getRandomDeveloper(),
                new Application(
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
                                database,
                                chaoticWorld,
                                componentRunTimes.getMapper()
                            ),
                            componentRunTimes.getLogic()
                        )
                    ),
                    new ThirdPartyResource("github", network),
                    componentRunTimes.getApplication()
                )
            }).toArray(size -> new Object[size][1]);
        return applications;
    }

    ChaoticWorld chaoticWorld = new ChaoticWorld();

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Application application) {
        int runTimeOfOperationsInBetween = application.failableAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
