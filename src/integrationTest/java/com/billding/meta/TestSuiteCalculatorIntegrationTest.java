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
// Some of these tests should be true functions in the codebase.
public class TestSuiteCalculatorIntegrationTest {
    private final Path outputFile;

    private Path createNewFile(String organization, String columnHeaders) throws IOException {
        Path path = Paths.get("./docs/_data/" + organization + ".csv");
        if (Files.exists(path)) Files.delete(path);
        String columnNames = columnHeaders + "\n";
        Files.write(path, columnNames.getBytes("UTF8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return path;
    }

    public TestSuiteCalculatorIntegrationTest() throws IOException {
        this.outputFile = Paths.get("./docs/_data/first.csv");
        Files.delete(this.outputFile);
        String columnNames = "organization,codebase,instances,runtime\n";
        Files.write(outputFile, columnNames.getBytes("UTF8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    void createTestData(String organizationName) throws IOException {
        Path path = createNewFile(organizationName, "codebase,dependencies,runtime");
        Organization organization = new Organization(organizationName);
        Stream.of( "adolescent", "established", "mature")
                .map(CodeBase::new)
                .forEach(codeBase -> Stream.of(
                        new InstanceGroupMockTimes(),
                        new InstanceGroupRealTimes()
                ).forEach(instanceGroup ->   {
                            TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(organization, codeBase, new TestingPeriod("moment"), instanceGroup);
                            long s = testSuiteCalculator.runTimeDuringWindow().getSeconds();
                            String formattedDuration = String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));

                            String line = Stream.of(
                                    codeBase.getName(),
                                    instanceGroup.getName(),
                                    formattedDuration
                            ).collect(Collectors.joining(",")) + "\n";

                            try {
                                Files.write(path, line.getBytes("UTF8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            } catch (IOException ex) { throw new RuntimeException(ex); }
                        }
                ));
    }
    @Test
    public void singleOrgData() throws IOException {
        createTestData("solo_project");
        createTestData("midsized");

    }

    @DataProvider(name = "scenarios")
    public static Object[][] logicInstances() {
        // This is absolutely RIPE for some decomposing.
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
                                        ).map(instanceGroup -> new Object[]{environment, codeBase, new TestingPeriod("moment"), instanceGroup});

                                    });
                        }

                );

        return scenarios
                .toArray(size -> new Object[size][2]);
    }


    @Test(dataProvider = "scenarios")
    public void test_scenarios_mocked(Organization organization, CodeBase codeBase, TestingPeriod testingPeriod, InstanceGroup instanceGroup) throws Exception {
        TestSuiteCalculator testSuiteCalculator = new TestSuiteCalculator(
                organization,
                codeBase,
                testingPeriod,
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

        Files.write(outputFile, line.getBytes("UTF8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

}
