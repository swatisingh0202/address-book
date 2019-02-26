package com.gumtree.addressbook.core.service;

import com.gumtree.addressbook.core.dto.Gender;
import com.gumtree.addressbook.core.dto.Person;

import java.util.List;

public interface PersonService {

    long findNumberOfPersonByGender(Gender gender);

    Person findOldestPerson();

    long findAgeDifferenceInDays(String person1, String person2);

    List<Person> findAll();
}
