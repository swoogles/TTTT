package com.billding.bad_versions;

import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertNotNull;

//@PrepareForTest(StaticLogic.class)
public class StaticControllerMockedTest extends PowerMockTestCase {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    /*
    @BeforeTest
    public void setup() {
        PowerMockito.mockStatic(StaticLogic.class);
    }
    */

    /*
    @DataProvider(name = "applications")
    public static Object[][] primeNumbers() {
        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();
        PowerMockito.mockStatic(StaticLogic.class);
//        PowerMockito.mockStaticPartial(Mocked.class, "methodToBeMocked");

        Mockito.when(StaticLogic.facilityLevelOperation()).thenReturn(mockedRunTime);
        Mockito.when(StaticLogic.failableAction()).thenReturn(mockedRunTime);
        Mockito.when(StaticLogic.getRunTime()).thenReturn(mockedRunTime);

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfControllerTests,
            (idx) -> new StaticController( ));
    }

    @Test(dataProvider = "applications")
    public void test_simple(String developer, Controller controller) {

        assertEquals(
        controller.facilityLevelOperation(),
                runTime
        );
        assertEquals(
            controller.getRunTime(),
                runTime
        );
        controller.failableAction();
    }
    */

    /*
    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }
    */


    @Test
    public void noData() {
        StaticController staticController = new StaticController();
        staticController.facilityLevelOperation();
        staticController.failableAction();
        assertNotNull(
                staticController.getRunTime()
        );

//        PowerMockito.mockStatic(StaticLogic.class);
//        Mockito.when(StaticLogic.facilityLevelOperation()).thenReturn(mockedRunTime);
    }
}
