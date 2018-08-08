package com.billding.boundary_tests;

import com.billding.meta.*;
import com.billding.tttt.Logic;
import com.billding.tttt.Mapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogicMockedTest {
    private static final Duration runTime = Duration.ofMillis(5);
    private static final Duration mockedRunTime = Duration.ofMillis(0);
    private static final World world = DemoScenarios.getPlatonicWorld();

    @DataProvider(name = "logics")
    public static Object[][] logicstances() {

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        Mapper mapper = mock(Mapper.class);
        when(mapper.fallibleAction()).thenReturn(mockedRunTime);
        when(mapper.getRunTime()).thenReturn(mockedRunTime);

        return testInstanceCreator.createInstances(
                CodeBase::getNumberOfLogicTests,
                (idx) -> new Logic(
                                mapper,
                                runTime
                        )
        );
    }

    @Test(dataProvider = "logics")
    public void test_specific(String developer, Logic logic) {
        Duration duration = logic.fallibleAction();
        world.do2AssertionsThatNeededToHappenInTheSameMinute(duration);
    }

}
