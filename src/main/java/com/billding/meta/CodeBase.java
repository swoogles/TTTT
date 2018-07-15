package com.billding.meta;

import com.billding.tttt.PropertyRetriever;

/**
 * Contains totals for the number of different types of tests in the project.
 */
public class CodeBase {
    private final int numberOfApplicationTests;
    private final int numberOfMapperTests;
    private final int numberOfLogicTests;
    private final int numberOfControllerTests;
    private final int numberOfThirdPartyResourceTests;
    private final int numberOfProducerTests;

    public CodeBase(String propertyFileName) {
        // TODO Consider less Stringy files.
        final PropertyRetriever propertyRetriever = new PropertyRetriever("codebases/" + propertyFileName);
        this.numberOfApplicationTests = propertyRetriever.getInt("num_tests.application");
        this.numberOfMapperTests = propertyRetriever.getInt("num_tests.mappers");
        this.numberOfLogicTests = propertyRetriever.getInt("num_tests.logic");
        this.numberOfControllerTests = propertyRetriever.getInt("num_tests.controllers");
        this.numberOfThirdPartyResourceTests = propertyRetriever.getInt("num_tests.third_party_resources");
        this.numberOfProducerTests = propertyRetriever.getInt("num_tests.producer");
    }

    public int getNumberOfApplicationTests() {
        return numberOfApplicationTests;
    }

    public int getNumberOfMapperTests() {
        return numberOfMapperTests;
    }

    public int getTotalNumberOfCasesRun() {
        return
            this.getNumberOfMapperTests()
                + this.getNumberOfApplicationTests()
                + this.getNumberOfControllerTests()
                + this.getNumberOfThirdPartyResourceTests();
    }

    public int getNumberOfControllerTests() {
        return numberOfControllerTests;
    }

    public int getNumberOfThirdPartyResourceTests() {
        return numberOfThirdPartyResourceTests;
    }

    public int getNumberOfProducerTests() {
        return numberOfProducerTests;
    }

    public int getNumberOfLogicTests() {
        return numberOfLogicTests;
    }
}
