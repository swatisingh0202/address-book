package com.gumtree.addressbook.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.gumtree.addressbook.core.dto.Gender;
import com.gumtree.addressbook.core.dto.Person;
import com.gumtree.addressbook.core.exception.NotFoundException;
import com.gumtree.addressbook.core.fileprocessor.CSVRecordData;
import com.gumtree.addressbook.core.fileprocessor.FileParser;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPersonServiceTest {

    @Mock
    private FileParser<CSVRecord> fileParser;

    @Mock
    private CSVRecordData csvRecordData;

    @InjectMocks
    private DefaultPersonService personService;

    @Before
    public void setUp() throws Exception {
        when(fileParser.parseFile(eq("location"), any(Function.class))).thenReturn(getPersonList());

        this.personService = new DefaultPersonService(fileParser, "location" , csvRecordData);
    }

    @Test
    public void givenPerson_whenPersonExist_thenReturnAgeDiff() {
        //When-then
        assertEquals(personService.findAgeDifferenceInDays("Bill McKnight", "Paul Robinson"), 2L);
    }

    @Test
    public void givenPerson_whenDoNotExist_thenThrowException() {
        Throwable exception = assertThrows(NotFoundException.class, () -> {
            personService.findAgeDifferenceInDays("Fake User", "Paul Robinson");
        });

        assertEquals("Person with name Fake User, not found", exception.getMessage());
    }

    @Test
    public void givenPersonList_whenFemaleExist_thenReturnCount() {
        //When-then
        assertEquals(personService.findNumberOfPersonByGender(Gender.FEMALE), 2);
    }

    @Test
    public void givenPersonList_whenMaleExist_thenReturnCount() {
        //When-then
        assertEquals(personService.findNumberOfPersonByGender(Gender.MALE), 2);
    }

    @Test
    public void givenPerson_whenOldestRequested_thenReturnOldest() {
        //When-then
        assertEquals(personService.findOldestPerson().getName(), "Paul Robinson");
    }

    private Person getPerson(String name, Gender gender, LocalDate dob) {
        return Person.builder()
                .name(name)
                .gender(gender)
                .dateOfBirth(Date.from(dob.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
    }

    private List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();

        personList.add(getPerson("Bill McKnight", Gender.MALE, LocalDate.now()));
        personList.add(getPerson("Paul Robinson", Gender.MALE, LocalDate.now().minusDays(2)));
        personList.add(getPerson("test1", Gender.FEMALE, LocalDate.now()));
        personList.add(getPerson("test2", Gender.FEMALE, LocalDate.now()));

        return personList;
    }

    private Map<Gender, Integer> getGenderMap() {
        Map<Gender, Integer> map = new HashMap<>();
        map.put(Gender.FEMALE, 2);
        map.put(Gender.MALE, 3);
        return map;
    }
}