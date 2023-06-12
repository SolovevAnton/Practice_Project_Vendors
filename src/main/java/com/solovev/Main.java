package com.solovev;

import com.solovev.repositories.CallsRepo;
import com.solovev.repositories.NewFilesRepo;
import com.solovev.repositories.ProcessedFilesRepo;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    /**
     * Method to process data based on task
     * @param rootDir directory with data
     */
    private static void workerMethod(Path rootDir) throws IOException {
        Path newDataDir = Path.of("new_data");
        Path processedDataDir = Path.of("processed_data");
        Path originalFilesProcessedDataDir = Path.of("processed");

        NewFilesRepo initialFilesRepo = new NewFilesRepo(rootDir,newDataDir);
        CallsRepo callsRepo = new CallsRepo(initialFilesRepo.getFiles());
        ProcessedFilesRepo processedVendorsRepo = new ProcessedFilesRepo(callsRepo.getCalls());

        processedVendorsRepo.save(rootDir,processedDataDir);
        initialFilesRepo.save(rootDir,processedDataDir.resolve(originalFilesProcessedDataDir));
    }
    public static void main(String[] args) {
        Path rootDir = Path.of("TestingData");
        int timeSleepMls = 10000;

        while(true){
            try {
                workerMethod(rootDir);
                Thread.sleep(timeSleepMls);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}