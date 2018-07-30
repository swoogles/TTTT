package com.billding.spring;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertNotNull;

public class ApplicationTest {
    @Test
    public void simple() {
        assertNotNull( new Application() );
        String[] args = (String[]) Arrays.asList("hi", "there").toArray();
        Application.main(args);
    }
}
