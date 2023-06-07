package com.solovev;

import com.solovev.repositories.CallsRepo;
import com.solovev.repositories.NewFilesRepo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path all = Paths.get("TestingData","new_data");
        Path file = Paths.get("D:\\Git\\Practice_Projects\\Operators\\TestingData\\new_data\\RE_FRAUD_LIST_20180706_152416_00000.txt");

        NewFilesRepo files = new NewFilesRepo(all);
        CallsRepo repo = new CallsRepo(files.getFiles());
        System.out.println(repo);
    }
}