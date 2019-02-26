package com.gumtree.addressbook.core.fileprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVFileParser implements FileParser<CSVRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileParser.class);

    @Override
    public <T> List<T> parseFile(String fileName, Function<CSVRecord, Optional<T>> mappingFunction) {
        try (
                Reader reader = new InputStreamReader(getInputStream(fileName));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            List<T> toReturn = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                mappingFunction.apply(csvRecord).ifPresent(toReturn::add);
            }

            return toReturn;
        } catch (IOException e) {
            LOGGER.warn("Unable to parse the record. {}", e);
        }

        return Collections.emptyList();
    }

    private static InputStream getInputStream(String fileName) {
        return Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
    }
}
