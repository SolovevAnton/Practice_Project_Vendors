package com.solovev;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.solovev.model.Call;
import com.solovev.repositories.CallsRepo;
import com.solovev.repositories.NewFilesRepo;
import com.solovev.repositories.ProcessedFilesRepo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path rootDir = Paths.get("TestingData");
        Path newDataDir = Path.of("new_data");
        Path processedDataDir = Path.of("processed_data");

        NewFilesRepo filesRepo = new NewFilesRepo(rootDir,newDataDir);
        CallsRepo callsRepo = new CallsRepo(filesRepo.getFiles());
        ProcessedFilesRepo finalRepo = new ProcessedFilesRepo(callsRepo.getCalls());

        finalRepo.save(rootDir,processedDataDir);

    }
}