package com.hanu.sec4.assignment;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public Flux<String> read(String path) {
        return Flux.generate(() -> openFile(Path.of(path)), FileReaderServiceImpl::readAndGetBufferedReader
                , this::closeBufferReader);
    }

    @SneakyThrows
    private BufferedReader openFile(Path path) {
        return Files.newBufferedReader(path);
    }

    private void closeBufferReader(BufferedReader bufferedReader) {
        try {
            bufferedReader.close();
            log.info("File closed successfully");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static BufferedReader readAndGetBufferedReader(BufferedReader bufferedReader, SynchronousSink<String> sink) {
        try {
            var name = bufferedReader.readLine();
            log.info("Reading the file content {}", name);
            if (name == null) {
                sink.complete();
            } else {
                sink.next(name);
            }
        } catch (IOException e) {
            sink.error(e);
        }
        return bufferedReader;
    }

     /* @Override
    public Flux<String> read(String path) {
        return Flux.generate(() -> Files.readAllLines(Path.of(path)).size(), (listOfNames, sink) -> {
            if (listOfNames == 0) {
                sink.complete();
            }
            try {
                if (listOfNames != 0) {
                    sink.next(Files.readAllLines(Path.of(path)).get(listOfNames - 1));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            listOfNames = listOfNames - 1;
            return listOfNames;
        }, listOfNames -> {
            System.out.println("Completed reading file");
        });
    }*/
}
