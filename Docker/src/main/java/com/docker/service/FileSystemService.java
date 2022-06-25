package com.docker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Slf4j
public class FileSystemService {

    public void saveToFile(String text) throws IOException {
        Files.write(Paths.get("./feedback/test.txt"),
                (text + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public String getContent() throws IOException {
        return convertToHtml(Files.readAllLines(Paths.get("./feedback/test.txt")));
    }

    private String convertToHtml(List<String> list) {
        return String.join("<br>", list);
    }
}
