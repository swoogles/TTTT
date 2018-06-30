package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestEnvironment;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationMockedTest {
    final ChaoticWorld chaoticWorld = new ChaoticWorld();

    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();

        final KafkaCluster kafkaCluster = mock(KafkaCluster.class);
        final AuthService authService = mock(AuthService.class);
        final Controller controller = mock(Controller.class);
        final ThirdPartyResource thirdPartyResource = mock(ThirdPartyResource.class);


        // TODO This might be automatically mocking the void failableAction method
        final UnreliableService mockSerice = mock(UnreliableService.class);
        // The hoops needed to test this class are pushing me more towards impelementing UnreliableService directly.
        when(mockSerice.getOperationRunTime()).thenReturn(1);
        when(kafkaCluster.getOperationRunTime()).thenReturn(1);
        when(authService.getOperationRunTime()).thenReturn(1);
        when(controller.getOperationRunTime()).thenReturn(1);
        when(thirdPartyResource.getOperationRunTime()).thenReturn(1);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            TestEnvironment::getNumberOfApplicationTests,
            (idx) -> new Application(
                        "test_app" + idx,
                        kafkaCluster,
                        authService,
                        controller,
                        thirdPartyResource,
                        componentRunTimes.getApplication()
                    )
        );
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Application application) {
        int runTimeOfOperationsInBetween = application.failableAction();
        application.simpleAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        chaoticWorld.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
