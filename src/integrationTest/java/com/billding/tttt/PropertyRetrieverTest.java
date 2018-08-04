package com.billding.tttt;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PropertyRetrieverTest {
    @Test
    public void test_simple() {
        PropertyRetriever propertyRetriever = new PropertyRetriever("service_status");
        assertTrue(propertyRetriever.getBinaryBoolean("database"));
    }
}
