package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ComponentRunTimesTest {
    private final ComponentRunTimes componentRunTimes = new ComponentRunTimes("frozen_runtimes");

    @Test
    public void getMapper() {
        assertEquals(componentRunTimes.getMapper(), 71);
    }

    @Test
    public void getDatabase() {
        assertEquals(componentRunTimes.getDatabase(), 73);
    }

    @Test
    public void getProducer() {
        assertEquals(componentRunTimes.getProducer(), 79);
    }

    @Test
    public void getNetwork() {
        assertEquals(componentRunTimes.getNetwork(), 83);
    }

    @Test
    public void getIntranet() {
        assertEquals(componentRunTimes.getIntranet(), 89);
    }

    @Test
    public void getLogic() {
        assertEquals(componentRunTimes.getLogic(), 97);
    }

    @Test
    public void getAuthService() {
        assertEquals(componentRunTimes.getAuthService(), 101);
    }

    @Test
    public void getController() {
        assertEquals(componentRunTimes.getController(), 103);
    }

    @Test
    public void getApplication() {
        assertEquals(componentRunTimes.getApplication(), 107);
    }

    @Test
    public void getSeleniumTest() {
        assertEquals(componentRunTimes.getSeleniumTest(), 109);
    }

    @Test
    public void getThirdPartyResource() {
        assertEquals(componentRunTimes.getThirdPartyResource(), 113);
    }
}
