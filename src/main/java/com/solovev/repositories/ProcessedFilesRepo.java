package com.solovev.repositories;

import com.solovev.model.Call;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

/**
 * Repository to store users and save them with files
 */
public class ProcessedFilesRepo {
    private final Path
    private Map<String, Map<LocalDate, Set<Call>>> calls = new HashMap<>();

    /**
     * Sorts calls in map dased on vendor and date
     * @param addedCalls calls to sort
     */
    public ProcessedFilesRepo(Collection<Call> addedCalls){
        for(Call call : addedCalls){
            String vendor = call.getVendor();
            LocalDate date = call.getDateFromFileName();

            calls.putIfAbsent(vendor, new HashMap<>());
            calls.get(vendor).putIfAbsent(date,new HashSet<>());
            calls.get(vendor).get(date).add(call);
        }
    }

    /**
     * Saves Calls in a processed dir, in a subdir with vendor name
     * Only calls with the isFraud == true are saved
     */
    public void save(){

    }

    @Override
    public String toString() {
        return "ProcessedFilesRepo{" +
                "calls=" + calls +
                '}';
    }
}
