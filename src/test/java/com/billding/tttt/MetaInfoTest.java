package com.billding.tttt;

import com.billding.meta.TestEnvironment;
import org.testng.annotations.Test;

public class MetaInfoTest {
    @Test
    public void reporting() {
        TestEnvironment testEnvironment = new TestEnvironment();
        System.out.println("Numer of cases to run: " + testEnvironment.getTotalNumberOfCasesRun());

    }
}
