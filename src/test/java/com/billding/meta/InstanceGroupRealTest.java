package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class InstanceGroupRealTest {
    InstanceGroupRealTimes instanceGroupRealTimes = new InstanceGroupRealTimes();

    @Test
    public void test_getters() {
        assertNotNull(instanceGroupRealTimes.getApplication());
        assertNotNull(instanceGroupRealTimes.getController());
        assertNotNull(instanceGroupRealTimes.getGithub());
        assertNotNull(instanceGroupRealTimes.getLogic());
        assertNotNull(instanceGroupRealTimes.getMapper());
        assertNotNull(instanceGroupRealTimes.getProducer());
        assertNotNull(instanceGroupRealTimes.getSeleniumTestClass());
    }

    @Test
    public void getName() {
        assertEquals(
                instanceGroupRealTimes.getName(),
                "Real"
        );
    }
}
