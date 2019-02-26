package com.gumtree.addressbook.core.fileprocessor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.csv.CSVRecord;


public interface FileParser {

    <T> List<T> parseFile(String fileName, Function<CSVRecord, Optional<T>> mappingFunction);
}
