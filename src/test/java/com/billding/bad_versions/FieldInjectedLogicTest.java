package com.billding.bad_versions;

import com.billding.meta.World;
import com.billding.tttt.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.when;

public class FieldInjectedLogicTest {
    @Mock
    World world;

    @Mock
    Mapper mapper;

    // We could have additional Mocks here, and not actually know if they're being used by
    // the class or not.

    @InjectMocks
    FieldInjectedLogic fieldInjectedLogic;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simple() {
        when(mapper.fallibleAction()).thenReturn(Duration.ofMillis(10));
        // Now if we forget this line, we get a runtime exception, rather than a compile error
        // Probably comment this out initially for the demo.
        fieldInjectedLogic.setRuntime(Duration.ofMillis(10));
        fieldInjectedLogic.failableAction();
    }
}
