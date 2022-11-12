package com.goit.devgroup.spring.ftapp.transfer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Primary
@Service
public class SaveFileTransferService implements TransferService {
    @Override
    public void transfer(byte[] input) {
        String filename = UUID.randomUUID() + ".zip";
        try {
            System.out.println("REALLY save content to " + filename);
            Files.write(Path.of(filename), input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
