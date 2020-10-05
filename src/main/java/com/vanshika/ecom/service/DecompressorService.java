package com.vanshika.ecom.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class DecompressorService {


    public byte[] decompressImage(byte[] compressedData) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] outputData = outputStream.toByteArray();
        return outputData;
    }
}
