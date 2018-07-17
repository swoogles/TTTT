package com.billding.meta;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Period;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class TestEnvironmentIntegrationTest {

    @Test
    public void validateEnvironments() throws IOException {
        validateEnvironments("./src/main/resources/test_environments");
    }

    private void validateEnvironments(String directoryPath) throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            paths
                    .filter(Files::isRegularFile)
                    .map(file->file.getFileName().toString())
                    .map(fileName->fileName.replace(".properties", ""))
                    .forEach(TestEnvironment::new);
        }

    }
}
