package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

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
    }
}
