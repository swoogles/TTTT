package com.billding.meta;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CodeBaseTest {
    private final CodeBase codeBase = new CodeBase("minimal");
    @Test
    public void simple() {
        assertTrue(
            codeBase.getTotalNumberOfCasesRun() > 0
        );
    }
}
