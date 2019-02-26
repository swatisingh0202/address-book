package com.gumtree.addressbook.core.fileprocessor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public interface FileParser<TYPE> {

    <T> List<T> parseFile(String fileName, Function<TYPE, Optional<T>> mappingFunction);
}
