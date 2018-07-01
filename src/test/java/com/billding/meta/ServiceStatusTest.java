package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ServiceStatusTest {

    @Test
    public void construct() {
        final ServiceStatus serviceStatus = new ServiceStatus();
        assertNotNull(serviceStatus);
    }

    @Test
    public void ensureServiceIsRunning_yes() {
        ServiceStatus.ensureServiceIsRunning("database");
    }

    @Test
    public void ensureServiceIsRunning_no() {
        ServiceStatus.ensureServiceIsRunning("network");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void ensureServiceIsRunning_nonexistent() {
        ServiceStatus.ensureServiceIsRunning("nonexistent service");
    }

}
