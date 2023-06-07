package com.solovev.repositories;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.solovev.model.Call;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * reads and stores calls from all files in the files collection
 */
public class CallsRepo {
    private Set<Call> calls = new HashSet<>();
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

    /**
     * Takes collection of files and reads all calls from them
     * Call date is represented as a part of the file name
     * @param files
     * @throws IOException
     */
    public CallsRepo(Collection<Path> files) throws IOException {
        for(Path path : files){
            MappingIterator<Call> callMappingIterator = objectReader.readValues(path.toFile());
            //set value of next call date taken from file name and ads it to set
            while(callMappingIterator.hasNext()) {
                Call nextCall = callMappingIterator.nextValue();
                String dateToParse = path.getFileName().toString()
                        .replaceAll("RE_FRAUD_LIST_","")
                        .replaceAll("_\\d{6}_\\d{5}\\.txt","");

                nextCall.setDateFromFileName(
                        LocalDate.parse(dateToParse, DateTimeFormatter.ofPattern("yyyyMMdd"))
                );
                calls.add(nextCall);
            }
        }
    }

    @Override
    public String toString() {
        return "CallsRepo{" +
                "calls=" + calls +
                '}';
    }
}
