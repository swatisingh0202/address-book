package com.gumtree.addressbook.core.fileprocessor;

import com.gumtree.addressbook.core.dto.Gender;
import com.gumtree.addressbook.core.dto.Person;
import com.gumtree.addressbook.core.service.DefaultPersonService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CSVRecordData {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonService.class);

    public  Optional<Person> createAddressBookData(CSVRecord csvRecord) {
        try {
            return Optional.of(Person.builder()
                        .name(csvRecord.get(0).trim())
                        .gender(Gender.fromString(csvRecord.get(1).trim()))
                        .dateOfBirth(SIMPLE_DATE_FORMAT.parse(csvRecord.get(2)))
                        .build());
        } catch (ParseException e) {
            LOGGER.error("Unable to parse the record. {}", e);
        } catch(Exception e){
            LOGGER.error("Unable to parse the record.{}", e);
        }
        return Optional.empty();
    }
}
