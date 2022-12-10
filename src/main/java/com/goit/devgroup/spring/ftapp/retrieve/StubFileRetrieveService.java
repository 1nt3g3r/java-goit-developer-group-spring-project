package com.goit.devgroup.spring.ftapp.retrieve;

import org.springframework.stereotype.Service;

@Service
public class StubFileRetrieveService implements FileRetrieveService {
    @Override
    public byte[] readFile(String path) {
        System.out.println("Make stub retrieve");
        return new byte[0];
    }
}
