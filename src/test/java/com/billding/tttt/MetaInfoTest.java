package com.billding.tttt;

import com.billding.meta.TestEnvironmentParameters;
import org.testng.annotations.Test;

public class MetaInfoTest {
    @Test
    public void reporting() {
        TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();
        System.out.println("Numer of cases to run: " + testEnvironmentParameters.getTotalNumberOfCasesRun());

    }
}
