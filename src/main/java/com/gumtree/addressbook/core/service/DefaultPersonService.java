package com.gumtree.addressbook.core.service;

import com.gumtree.addressbook.core.dto.Gender;
import com.gumtree.addressbook.core.dto.Person;
import com.gumtree.addressbook.core.exception.NotFoundException;
import com.gumtree.addressbook.core.fileprocessor.FileParser;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultPersonService implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonService.class);

    private final String fileLocation;

    private final FileParser<CSVRecord> fileParser;

    private final List<Person> personList = new ArrayList<>();

    private final AddressBookMapper addressBookMapper;

    @Autowired
    public DefaultPersonService(FileParser<CSVRecord> fileParser, @Value("${address.book.file.location}") String fileLocation , AddressBookMapper addressBookMapper) {
        this.fileParser = fileParser;
        this.fileLocation = fileLocation;
        this.addressBookMapper = addressBookMapper;
        initPersonList();
    }

    @Override
    public long findNumberOfPersonByGender(Gender gender) {
        return personList.stream()
                .filter(person -> person.getGender().equals(gender))
                .count();
    }

    @Override
    public Person findOldestPerson() {
        return personList.stream()
                .reduce((p1, p2) -> p1.getDateOfBirth().before(p2.getDateOfBirth()) ? p1 : p2)
                .orElseThrow(() -> new NotFoundException("Person does not exist in address book"));
    }

    @Override
    public long findAgeDifferenceInDays(String name1, String name2) {
        Person p1 = findByName(name1);
        Person p2 = findByName(name2);

        return p1.getDateOfBirth()
                .before(p2.getDateOfBirth()) ? calculateAgeDifference(p1, p2) : calculateAgeDifference(p2, p1);
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(personList);
    }

    private void initPersonList() {
        personList.addAll(fileParser.parseFile(fileLocation, addressBookMapper::createAddressBookData));
    }

    private Person findByName(String name) throws NotFoundException {
        return personList.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Person with name %s, not found", name)));
    }

    private long calculateAgeDifference(Person older, Person younger) {
        LocalDate olderPersonAge = toLocalDate(older.getDateOfBirth());
        LocalDate youngerPersonAge = toLocalDate(younger.getDateOfBirth());

        return ChronoUnit.DAYS.between(olderPersonAge, youngerPersonAge);
    }

    private LocalDate toLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
    }

}
