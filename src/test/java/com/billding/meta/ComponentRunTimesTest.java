package com.billding.meta;

import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ComponentRunTimesTest {
    private final ComponentRunTimes componentRunTimes = new ComponentRunTimes("frozen_runtimes");

    @Test
    public void getMapper() {
        assertEquals(componentRunTimes.getMapper(), Duration.ofMillis(71));
    }

    @Test
    public void getDatabase() {
        assertEquals(componentRunTimes.getDatabase(), Duration.ofMillis(73));
    }

    @Test
    public void getProducer() {
        assertEquals(componentRunTimes.getProducer(), Duration.ofMillis(79));
    }

    @Test
    public void getNetwork() {
        assertEquals(componentRunTimes.getNetwork(), Duration.ofMillis(83));
    }

    @Test
    public void getIntranet() {
        assertEquals(componentRunTimes.getIntranet(), Duration.ofMillis(89));
    }

    @Test
    public void getLogic() {
        assertEquals(componentRunTimes.getLogic(), Duration.ofMillis(97));
    }

    @Test
    public void getAuthService() {
        assertEquals(componentRunTimes.getAuthService(), Duration.ofMillis(101));
    }

    @Test
    public void getController() {
        assertEquals(componentRunTimes.getController(), Duration.ofMillis(103));
    }

    @Test
    public void getApplication() {
        assertEquals(componentRunTimes.getApplication(), Duration.ofMillis(107));
    }

    @Test
    public void getSeleniumTest() {
        assertEquals(componentRunTimes.getSeleniumTest(), Duration.ofMillis(109));
    }

    @Test
    public void getThirdPartyResource() {
        assertEquals(componentRunTimes.getThirdPartyResource(), Duration.ofMillis(113));
    }

    @Test
    public void getBrowser() {
        assertEquals(componentRunTimes.getBrowser(), Duration.ofMillis(127));
    }

    @Test
    public void getKafkaCluster() {
        assertEquals(componentRunTimes.getKafkaCluster(), Duration.ofMillis(131));
    }

    @Test
    public void getWebDriver() {
        assertEquals(componentRunTimes.getWebDriver(), Duration.ofMillis(137));
    }
}
