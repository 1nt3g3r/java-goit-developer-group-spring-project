package com.goit.devgroup.spring.ftapp.encrypt;

import org.springframework.stereotype.Service;

@Service
public class StubEncryptService implements EncryptService {
    @Override
    public byte[] encrypt(byte[] input) {
        System.out.println("Make stub encrypt");
        return input;
    }
}
