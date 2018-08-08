package com.billding.boundary_tests;

import com.billding.meta.*;
import com.billding.tttt.*;
import com.billding.tttt.external_services.ThirdPartyResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    private static final World world = DemoScenarios.getPlatonicWorld();

    @DataProvider(name = "applications")
    public static Object[][] applications() {
        final Producer producer = mock(Producer.class);
        final AuthService authService = mock(AuthService.class);
        final Controller controller = mock(Controller.class);
        final ThirdPartyResource thirdPartyResource = mock(ThirdPartyResource.class);


        // TODO This might be automatically mocking the void fallibleAction method
        final UnreliableService mockSerice = mock(UnreliableService.class);
        // The hoops needed to test this class are pushing me more towards impelementing UnreliableService directly.
        when(mockSerice.getRunTime()).thenReturn(mockedRunTime);
        when(producer.getRunTime()).thenReturn(mockedRunTime);
        when(producer.fallibleAction()).thenReturn(mockedRunTime);
        when(authService.getRunTime()).thenReturn(mockedRunTime);
        when(authService.fallibleAction()).thenReturn(mockedRunTime);
        when(controller.getRunTime()).thenReturn(mockedRunTime);
        when(controller.fallibleAction()).thenReturn(mockedRunTime);
        when(thirdPartyResource.getRunTime()).thenReturn(mockedRunTime);
        when(thirdPartyResource.fallibleAction()).thenReturn(mockedRunTime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfApplicationTests,
            (idx) -> new Application(
                        "test_app" + idx,
                        producer,
                        authService,
                        controller,
                        thirdPartyResource,
                    runTime
                    )
        );
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Application application) {
        Duration runTimeOfOperationsInBetween = application.fallibleAction();
        application.fallibleAction();
//        assertEquals(runTimeOfOperationsInBetween, 10);
        world.do2AssertionsThatNeededToHappenInTheSameMinute(runTimeOfOperationsInBetween);
    }
}
