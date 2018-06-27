package com.billding.tttt;

import org.testng.annotations.Test;

public class ServiceStatusTest {
    @Test
    public void database_is_running() {
        ServiceStatus.ensureServiceIsRunning("database");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void nonexistent_service_not_running() {
        ServiceStatus.ensureServiceIsRunning("NONEXISTENT SERVICE");
    }
}
