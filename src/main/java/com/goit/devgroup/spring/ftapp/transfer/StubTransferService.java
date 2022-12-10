package com.goit.devgroup.spring.ftapp.transfer;

import org.springframework.stereotype.Service;

@Service
public class StubTransferService implements TransferService {
    @Override
    public void transfer(byte[] input) {
        System.out.println("Make stub transfer " + input.length + " bytes");
    }
}
