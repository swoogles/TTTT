package com.billding.tttt;

import com.billding.meta.TestEnvironment;
import org.testng.annotations.Test;

// TODO This test should probably go away now that TestSuiteCalculator exists
public class MetaInfoTest {
    @Test
    public void reporting() {
        TestEnvironment testEnvironment = new TestEnvironment("midsized_organization");
        System.out.println("Numer of cases to run: " + testEnvironment.getTotalNumberOfCasesRun());

    }
}
