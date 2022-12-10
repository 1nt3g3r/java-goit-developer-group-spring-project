package com.goit.devgroup.spring.ftapp.compress;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Primary
@Service
public class GzipCompressService implements CompressService {
    @Override
    public byte[] compress(byte[] input) {
        try {
            return doSomePrivateWork(input);
        } catch (Exception ex) {
            ex.printStackTrace();

            throw new IllegalStateException(ex);
        }

    }

    private byte[] doSomePrivateWork(byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(baos);
        ZipEntry zipEntry = new ZipEntry(UUID.randomUUID().toString());
        zipOut.putNextEntry(zipEntry);
        zipOut.write(input, 0, input.length);
        zipOut.close();

        byte[] result = baos.toByteArray();
        baos.close();

        System.out.println("REALLY compress data. Before: " + input.length + ", after: " + result.length);
        return result;
    }
}
