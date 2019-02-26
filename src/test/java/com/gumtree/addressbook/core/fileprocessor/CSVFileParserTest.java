package com.gumtree.addressbook.core.fileprocessor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gumtree.addressbook.core.dto.Gender;
import com.gumtree.addressbook.core.dto.Person;

import java.util.Optional;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVFileParserTest {

    @InjectMocks
    private CSVFileParser csvFileParser;

    private final String fileLocation = "addressBook.csv";

    @Test
    public void shouldReturnPersonList() {
        assertNotNull(csvFileParser.parseFile(fileLocation, this::createAddressBookTest));
        assertEquals(csvFileParser.parseFile(fileLocation, this::createAddressBookTest).size(), 5);
    }

    private Optional<Person> createAddressBookTest(CSVRecord csvRecord) {
        return Optional.of(Person.builder()
                .name(csvRecord.get(0).trim())
                .gender(Gender.fromString(csvRecord.get(1).trim()))
                .build());
    }

}