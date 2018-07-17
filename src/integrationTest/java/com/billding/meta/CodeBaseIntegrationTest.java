package com.billding.meta;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CodeBaseIntegrationTest {

    @Test
    public void validateEnvironments() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("./src/main/resources/codebases"))) {
            paths
                    .filter(Files::isRegularFile)
                    .map(file->file.getFileName().toString())
                    .map(fileName->fileName.replace(".properties", ""))
                    .forEach(CodeBase::new);
        }
    }

}
