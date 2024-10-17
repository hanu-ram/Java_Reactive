package com.hanu.sec2.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public final class IoReactiveAssignmentImpl implements IOReactiveAssignment {

    @Override
    public Mono<String> readFromFile(String filePath) {
        return Mono.fromCallable(() -> {
            byte[] bytes = Files.readAllBytes(Path.of(filePath));
            return new String(bytes);
        });
    }

    @Override
    public Mono<Void> writeToFile(String filePath, String content) {
        return Mono.fromRunnable(() -> {
            try {
                Files.write(Path.of(filePath), content.getBytes());
            } catch (IOException e) {
                log.error("Error writing to file", e);
            }
        });
    }

    @Override
    public Mono<Void> deleteFile(String filePath) {
        return Mono.fromRunnable(() -> {
            try {
                Files.delete(Path.of(filePath));
            } catch (IOException e) {
                log.error("Error deleting file", e);
            }
        });
    }
}

