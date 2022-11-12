package com.goit.devgroup.spring.ftapp.encrypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Primary
@Service
public class XorEncryptService implements EncryptService {
    @Value("${security.encrypt.key}")
    private byte key;

    @Override
    public byte[] encrypt(byte[] input) {
        System.out.println("REALLY encrypt " + input.length + " bytes, key is: " + key);
        byte[] copy = Arrays.copyOf(input, input.length);

        for (int i = 0; i < copy.length; i++) {
            copy[i] = (byte) (copy[i] ^ key);
        }

        return copy;
    }
}
