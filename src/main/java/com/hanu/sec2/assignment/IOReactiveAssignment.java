package com.hanu.sec2.assignment;

import reactor.core.publisher.Mono;

public interface IOReactiveAssignment {
    Mono<String> readFromFile(String filePath);
    Mono<Void> writeToFile(String filePath, String content);
    Mono<Void> deleteFile(String filePath);
}
