package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class InstanceGroupMockedTest {
    InstanceGroupMockTimes instanceGroupMockTimes = new InstanceGroupMockTimes();

    @Test
    public void test_getters() {
        assertNotNull(instanceGroupMockTimes.getApplication());
        assertNotNull(instanceGroupMockTimes.getController());
        assertNotNull(instanceGroupMockTimes.getGithub());
        assertNotNull(instanceGroupMockTimes.getLogic());
        assertNotNull(instanceGroupMockTimes.getMapper());
        assertNotNull(instanceGroupMockTimes.getProducer());
        assertNotNull(instanceGroupMockTimes.getSeleniumTestClass());
    }

    @Test
    public void getName() {
        assertEquals(
                instanceGroupMockTimes.getName(),
                "Mocks"
        );
    }
}
