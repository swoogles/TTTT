package com.billding.tttt;

import org.testng.annotations.Test;

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
}
