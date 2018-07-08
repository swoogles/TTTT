package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    private final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final KafkaCluster kafkaCluster = mock(KafkaCluster.class);
        final AuthService authService = mock(AuthService.class);
        final Controller controller = mock(Controller.class);
        final ThirdPartyResource thirdPartyResource = mock(ThirdPartyResource.class);


        // TODO This might be automatically mocking the void failableAction method
        final UnreliableService mockSerice = mock(UnreliableService.class);
        // The hoops needed to test this class are pushing me more towards impelementing UnreliableService directly.
        when(mockSerice.getRunTime()).thenReturn(mockedRunTime);
        when(kafkaCluster.getRunTime()).thenReturn(mockedRunTime);
        when(kafkaCluster.clusterAction()).thenReturn(mockedRunTime);
        when(authService.getRunTime()).thenReturn(mockedRunTime);
        when(authService.authenticateUser("userName", "password")).thenReturn(mockedRunTime);
        when(controller.getRunTime()).thenReturn(mockedRunTime);
        when(controller.facilityLevelOperation()).thenReturn(mockedRunTime);
        when(thirdPartyResource.getRunTime()).thenReturn(mockedRunTime);
        when(thirdPartyResource.communicate()).thenReturn(mockedRunTime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfApplicationTests,
            (idx) -> new Application(
                        "test_app" + idx,
                        kafkaCluster,
                        authService,
                        controller,
                        thirdPartyResource,
                    runTime
                    )
        );
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Application application) {
        Duration runTimeOfOperationsInBetween = application.failableAction();
        application.simpleAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
