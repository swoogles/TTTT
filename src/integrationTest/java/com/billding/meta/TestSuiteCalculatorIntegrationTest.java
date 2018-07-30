package com.billding.meta;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO These are important examples. Make sure to highlight them in presentation.
public class TestSuiteCalculatorIntegrationTest {
    private final Path outputFile;

    public TestSuiteCalculatorIntegrationTest() throws IOException {
        System.out.println(Paths.get(".").toAbsolutePath());
        System.out.println("|organization | codebase | instances | runtime |");
        System.out.println("|-------|--------|---------|--------|\n");
        this.outputFile = Paths.get("./docs/_data/first.csv");
        Files.delete(this.outputFile);
        String columnNames = "organization,codebase,instances,runtime\n";
        Files.write(outputFile, columnNames.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @DataProvider(name = "scenarios")
    public static Object[][] logicInstances() {
        Stream<Object[]> scenarios = Stream.of(
                "solo_project", "startup", "midsized")
                .map(Organization::new)
                .flatMap(environment -> {
                            return Stream.of(
                                    "minimal", "adolescent", "established", "mature")
                                    .map(CodeBase::new)
                                    .flatMap(codeBase -> {
                                        return Stream.of(
                                                new InstanceGroupMockTimes(),
                                                new InstanceGroupRealTimes()
                                        ).map(instanceGroup -> new Object[]{environment, codeBase, instanceGroup});

                                    });
                        }

                );

        return scenarios
                .toArray(size -> new Object[size][2]);
    }


    @Test(dataProvider = "scenarios")
    public void test_scenarios_mocked(Organization organization, CodeBase codeBase, InstanceGroup instanceGroup) throws Exception {
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                organization,
                codeBase,
                instanceGroup
        );
        // This isn't factoring in environment AT ALL. Only codebases and the instances.
        /*
        System.out.println(
                "environment: " + organization.getName()
                + " codebase: " + codeBase.getName()
                + " single runtime: "  + testSuiteCalculator.totalTestRunTime()
        );
        */

        long s = testSuiteCalculator.runTimeDuringWindow().getSeconds();
        String formattedDuration = String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));

        String line = Stream.of(
                organization.getName(),
                codeBase.getName(),
                instanceGroup.getName(),
                formattedDuration
        ).collect(Collectors.joining(",")) + "\n";

        Files.write(outputFile, line.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        System.out.println(
                "| " + organization.getName()
                        + " | " + codeBase.getName()
                        + " | " + instanceGroup.getName()
                        + " | "  + formattedDuration
                + " |"
        );
    }

}
