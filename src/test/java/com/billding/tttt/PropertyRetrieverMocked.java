package com.billding.tttt;

import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PropertyRetrieverMocked {
    private final PropertyRetriever propertyRetriever = new PropertyRetriever("retriever");

    @Test
    public void test_getBoolean_true() {
        assertTrue(propertyRetriever.getBoolean("true_boolean"));
    }

    @Test
    public void test_getBoolean_false() {
        assertFalse(propertyRetriever.getBoolean("false_boolean"));
    }

    @Test
    public void test_getInt() {
        assertEquals(propertyRetriever.getInt("int"), 7);
    }


    @Test
    public void test_getString() {
        assertEquals(propertyRetriever.getString("string"), "test string value");
    }

    @Test
    public void getShortDuration_milliseconds() {
        assertEquals(propertyRetriever.getShortDuration("short_duration.milliseconds"), Duration.ofMillis(20));
    }

    @Test
    public void getShortDuration_seconds() {
        assertEquals(propertyRetriever.getShortDuration("short_duration.seconds"), Duration.ofSeconds(5));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getShortDuration_invalid() {
        assertEquals(propertyRetriever.getShortDuration("short_duration.invalid"), Duration.ofSeconds(5));
    }
}
