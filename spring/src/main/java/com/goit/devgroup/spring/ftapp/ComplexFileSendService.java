package com.goit.devgroup.spring.ftapp;

import com.goit.devgroup.spring.ftapp.compress.CompressService;
import com.goit.devgroup.spring.ftapp.encrypt.EncryptService;
import com.goit.devgroup.spring.ftapp.retrieve.FileRetrieveService;
import com.goit.devgroup.spring.ftapp.transfer.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class ComplexFileSendService {
    private final FileRetrieveService fileRetrieveService;
    private final EncryptService encryptService;
    private final CompressService compressService;
    private final TransferService transferService;

    @PostConstruct
    public void init() {
        String filePath = "./build.gradle";
        System.out.println("Starting send file " + filePath + "...");

        byte[] raw = fileRetrieveService.readFile(filePath);
        byte[] encrypted = encryptService.encrypt(raw);
        byte[] compressed = compressService.compress(encrypted);
        transferService.transfer(compressed);
    }
}
