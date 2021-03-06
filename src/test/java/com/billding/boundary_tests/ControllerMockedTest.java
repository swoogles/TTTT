package com.billding.boundary_tests;

import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.Controller;
import com.billding.tttt.Logic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ControllerMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    @DataProvider(name = "controllers")
    public static Object[][] controllers() {
        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        final Logic logicMock = mock(Logic.class);
        when (logicMock.fallibleAction()).thenReturn(mockedRunTime);
        when (logicMock.getRunTime()).thenReturn(mockedRunTime);

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfControllerTests,
            (idx) -> new Controller(
                    runTime,
                logicMock
            ));
    }

    @Test(dataProvider = "controllers")
    public void test_simple(String developer, Controller controller) {

        assertEquals(
        controller.fallibleAction(),
                runTime
        );
        assertEquals(
            controller.getRunTime(),
                runTime
        );
    }

}
