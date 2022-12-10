package com.goit.devgroup.spring.ftapp.compress;

import org.springframework.stereotype.Service;

@Service
public class StubCompressService implements CompressService {
    @Override
    public byte[] compress(byte[] input) {
        System.out.println("Make stub compress ...");
        return input;
    }
}
