package com.hanu.sec3.assignment;

import reactor.core.publisher.Mono;

public sealed interface IOReactiveAssignment permits IoReactiveAssignmentImpl {
    Mono<String> readFromFile(String filePath);
    Mono<Void> writeToFile(String filePath, String content);
    Mono<Void> deleteFile(String filePath);
}
