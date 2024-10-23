package com.hanu.sec4.assignment;

import reactor.core.publisher.Flux;

public interface FileReaderService {
    Flux<String> read(String path);
}
