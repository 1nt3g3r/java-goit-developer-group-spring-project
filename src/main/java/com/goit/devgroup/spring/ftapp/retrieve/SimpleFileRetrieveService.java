package com.goit.devgroup.spring.ftapp.retrieve;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Primary
@Service
public class SimpleFileRetrieveService implements FileRetrieveService {
    @Override
    public byte[] readFile(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Path.of(path));

            System.out.println("REALLY retrieve " + bytes.length + " bytes");

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
