package com.solovev.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Repository to store unique files from the folder, that are named like:
 * RE_FRAUD_LIST_yyyyMMdd_000000_00000.txt
 */
public class NewFilesRepo {
    /**
     * Pattern with minimum check for date correctness
     */
    private final String pattern = "RE_FRAUD_LIST_20\\d{2}[01]\\d{3}_\\d{6}_\\d{5}\\.txt";
    private final Path dirName = Paths.get("new_data");
    private final Set<Path> files = new HashSet<>();

    /**
     * Adds unique files from the dirName, not its sub-dirs, that matches pattern
     * If there is no subDirectory, matches dir name IO will be thrown
     * @param path dir to check
     * @throws IOException if there is no dirName subdir in path
     */
    public NewFilesRepo(Path path) throws IOException {
        Path pathWithDir = path.resolve(dirName);
        Files //todo try with resources?
                .walk(pathWithDir, 1)
                .filter(this::check)
                .forEach(files::add);
    }

    /**
     * Checks if file have the name with pattern RE_FRAUD_LIST_yyyyMMdd_000000_00000.txt
     *
     * @param path to check
     * @return true if matches the pattern, false otherwise
     */
    private boolean check(Path path) {
        return path.getFileName().toString().matches(pattern);
    }

    public Set<Path> getFiles() {
        return Collections.unmodifiableSet(files);
    }

    @Override
    public String toString() {
        return "NewFilesRepo{" +
                "files=" + files +
                '}';
    }
}
