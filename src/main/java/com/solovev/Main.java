package com.solovev;

import com.solovev.repositories.CallsRepo;
import com.solovev.repositories.NewFilesRepo;
import com.solovev.repositories.ProcessedFilesRepo;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    private static final Path ROOT_DIR = Path.of("TestingData");

    /**
     * Method to process data based on task
     */
    private static void workerMethod() throws IOException {
        Path newDataDir = Path.of("new_data");
        Path processedDataDir = Path.of("processed_data");
        Path originalFilesProcessedDataDir = Path.of("processed");

        NewFilesRepo initialFilesRepo = new NewFilesRepo(ROOT_DIR,newDataDir);
        CallsRepo callsRepo = new CallsRepo(initialFilesRepo.getFiles());
        ProcessedFilesRepo processedVendorsRepo = new ProcessedFilesRepo(callsRepo.getCalls());

        processedVendorsRepo.save(ROOT_DIR,processedDataDir);
        initialFilesRepo.save(ROOT_DIR,processedDataDir.resolve(originalFilesProcessedDataDir));
    }
    public static void main(String[] args) {

    }
}