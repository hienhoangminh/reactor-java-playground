package com.rp.sec01.assignment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtil {

    public static String readFile(String path) {
        byte[] content = new byte[0];
        try {
            content = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(content);
    }


    public static void writeContent(String path, String content) {
        Path filePath = Paths.get(path);
        byte[] strToBytes = content.getBytes();
        try {
            Files.write(filePath, strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


