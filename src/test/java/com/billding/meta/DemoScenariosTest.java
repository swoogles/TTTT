package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class DemoScenariosTest {
    @Test
    public void simple() {
        new DemoScenarios();
        assertNotNull(
            DemoScenarios.getTestEnvironment()
        );
        assertNotNull(
                DemoScenarios.getWorld()
        );
        // TODO This is susceptible to failure during unrelated live demos
        assertFalse(
                DemoScenarios.shouldUseRealRuntimes()
        );
        assertTrue(
                DemoScenarios.getNumberOfTimesTestWillBeRun() > 0
        );
    }
}
