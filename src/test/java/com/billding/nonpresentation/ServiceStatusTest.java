package com.billding.nonpresentation;

import com.billding.meta.ServiceStatus;
import org.testng.annotations.Test;

public class ServiceStatusTest {
    @Test
    public void database_is_running() {
        ServiceStatus.ensureServiceIsRunning("database");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void service_is_not_running() {
        // If I parameterize the property file, I won't have to use this bogus property.
        ServiceStatus.ensureServiceIsRunning("test_data_loaded");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void nonexistent_service_not_running() {
        ServiceStatus.ensureServiceIsRunning("NONEXISTENT SERVICE");
    }
}
