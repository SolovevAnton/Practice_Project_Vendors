package com.solovev;

import com.solovev.repositories.CallsRepo;
import com.solovev.repositories.NewFilesRepo;
import com.solovev.repositories.ProcessedFilesRepo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path all = Paths.get("TestingData");
        Path file = Paths.get("D:\\Git\\Practice_Projects\\Operators\\TestingData\\new_data\\RE_FRAUD_LIST_20180706_152416_00000.txt");

        NewFilesRepo filesRepo = new NewFilesRepo(all);
        CallsRepo callsRepo = new CallsRepo(filesRepo.getFiles());
        ProcessedFilesRepo finalRepo = new ProcessedFilesRepo(callsRepo.getCalls());

        System.out.println(finalRepo);
    }
}