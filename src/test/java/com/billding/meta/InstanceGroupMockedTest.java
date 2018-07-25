package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class InstanceGroupMockedTest {

    @Test
    public void test_getters() {
        InstanceGroupMockTimes instanceGroupMockTimes = new InstanceGroupMockTimes();
        assertNotNull(instanceGroupMockTimes.getApplication());
        assertNotNull(instanceGroupMockTimes.getController());
        assertNotNull(instanceGroupMockTimes.getGithub());
        assertNotNull(instanceGroupMockTimes.getLogic());
        assertNotNull(instanceGroupMockTimes.getMapper());
        assertNotNull(instanceGroupMockTimes.getProducer());
        assertNotNull(instanceGroupMockTimes.getSeleniumTestClass());
    }
}
