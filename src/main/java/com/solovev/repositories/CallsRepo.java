package com.solovev.repositories;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.solovev.model.Call;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * reads and stores calls from all files in the files collection
 */
public class CallsRepo {
    Set<Call> calls = new HashSet<>();
    /**
     * Schema used to parse this csv
     */
    private CsvSchema schema = CsvSchema.emptySchema()
            .withColumnSeparator('|')
            .withHeader()
            .withSkipFirstDataRow(true);
    /**
     * CSV mapper with this schema
     */
    private ObjectReader objectReader = new CsvMapper()
            .findAndRegisterModules()
            .readerFor(Call.class)
            .with(schema);
    public CallsRepo(Path path) throws IOException {
        Call read = objectReader.readValue(path.toFile());
    }
}
