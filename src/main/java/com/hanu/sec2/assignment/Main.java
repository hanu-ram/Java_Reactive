package com.hanu.sec2.assignment;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        var service = new IoReactiveAssignmentImpl();
        service.readFromFile("src/main/resources/sample.txt")
                .subscribe(
                        Util.subscriber()
                );
        service.writeToFile("src/main/resources/sample.txt", "HI Hanumanth. Are you busy?").subscribe();
    }

}
