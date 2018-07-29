package com.billding.meta;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestEnvironmentIntegrationTest {

    @Test
    public void validateEnvironments() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("./src/main/resources/test_environments"))) {
            paths
                .filter(Files::isRegularFile)
                .map(file->file.getFileName().toString())
                .map(fileName->fileName.replace(".properties", ""))
                .forEach(Organization::new);
        }
    }

}
