package com.solovev.repositories;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.solovev.model.Call;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository to store users and save them with files
 */
public class ProcessedFilesRepo {
    private final Map<String, Map<LocalDate, Set<Call>>> calls = new HashMap<>();
    /**
     * Schema used to parse this csv
     */
    private final CsvSchema schema = new CsvMapper() //todo how to add empty raw?
            .schemaFor(Call.class)
            .withHeader()
            .withColumnSeparator('|')
            .withSkipFirstDataRow(true)
            .withoutQuoteChar();
    /**
     * CSV mapper with this schema
     */
    private final ObjectWriter objectWriter = new CsvMapper()
            .findAndRegisterModules()
            .writer()
            .with(schema);

    /**
     * Sorts calls in map dased on vendor and date
     *
     * @param addedCalls calls to sort
     */
    public ProcessedFilesRepo(Collection<Call> addedCalls) {
        for (Call call : addedCalls) {
            String vendor = call.getVendor();
            LocalDate date = call.getDateFromFileName();

            calls.putIfAbsent(vendor, new HashMap<>());
            calls.get(vendor).putIfAbsent(date, new HashSet<>());
            calls.get(vendor).get(date).add(call);
        }
    }

    /**
     * Saves Calls in ths repo in processed dir, in a subdir with vendor name
     * Only calls with the isFraud == true are saved
     * Calls are saved in a files with following name pattern: VENDOR_FRAUD_LIST_yyyyMMdd_*.0.txt,
     *
     * @param parentDir where to create processedDir to create vendor folders and save files
     * @param processedDir where to store dir with vendors dirs and files
     */
    public void save(Path parentDir, Path processedDir) throws IOException {
        for (String vendor : calls.keySet()) {
            Path dirToSave = parentDir.resolve(processedDir).resolve(vendor);
            dirToSave.toFile().mkdirs(); //todo why fails without this?
            //counter for file number in vendor dir
            long counterOfFilesInVendorDir = dirToSave.toFile().exists() ?
                    Files.walk(dirToSave, 1).count() - 1 //-1 not to count directory itself
                    : 0;

            for (LocalDate date : calls.get(vendor).keySet()) {

                String fileName = String.format("%s_FRAUD_LIST_%s_%d.txt",
                        vendor.toUpperCase(),
                        date.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                        counterOfFilesInVendorDir++); //todo what exactly pattern?

                File fileToSave = dirToSave.resolve(fileName).toFile();
                save(fileToSave, calls.get(vendor).get(date));
            }
        }
    }

    /**
     * Method to save given collection of calls to the given file in csv format with csv schema given in class
     *
     * @param fileToSave     file to save calls
     * @param callCollection calls to save
     */
    public void save(File fileToSave, Collection<Call> callCollection) throws IOException {
        Set<Call> filteredCalls = callCollection.stream().filter(Call::getFraud).collect(Collectors.toSet());
         objectWriter.writeValues(fileToSave).writeAll(filteredCalls).close();
    }


    @Override
    public String toString() {
        return "ProcessedFilesRepo{" +
                "calls=" + calls +
                '}';
    }
}
