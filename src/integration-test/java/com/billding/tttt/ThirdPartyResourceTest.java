package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class ThirdPartyResourceTest {
    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());

        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();

        final Object[][] applications =
            IntStream.range(0, testEnvironmentParameters.getNumberOfThirdPartyResourceTests())
                .mapToObj(idx -> new Object[]{
                    testEnvironmentParameters.getRandomDeveloper(),
                    new ThirdPartyResource("github", network)
                }).toArray(size -> new Object[size][1]);
        return applications;
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, ThirdPartyResource thirdPartyResource) {
        int runTimeOfOperationsInBetween = thirdPartyResource.failableAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
