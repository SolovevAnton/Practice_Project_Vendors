package com.solovev;

import com.solovev.repositories.NewFilesRepo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path correct = Paths.get("TestingData","new_data");
        Path inCorrect = Paths.get("D:\\Git\\Practice_Projects\\Operators");

        NewFilesRepo correctRepo = new NewFilesRepo(correct);
        NewFilesRepo inCorrectRepo = new NewFilesRepo(inCorrect);
        System.out.println(correctRepo);
        System.out.println(inCorrectRepo);

    }
}