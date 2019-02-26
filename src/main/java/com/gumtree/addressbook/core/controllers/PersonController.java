package com.gumtree.addressbook.core.controllers;

import com.gumtree.addressbook.core.dto.Gender;
import com.gumtree.addressbook.core.dto.Person;
import com.gumtree.addressbook.core.service.PersonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Person> getPersons() {
        return personService.findAll();
    }

    @RequestMapping(value = "/oldest", method = RequestMethod.GET, produces = "application/json")
    public Person getOldestPerson() {
        return personService.findOldestPerson();
    }

    @RequestMapping(value = "/ageDifference", method = RequestMethod.GET, produces = "application/json")
    public long getAgeDifferenceInDays(@RequestParam(required = false, defaultValue = "Bill McKnight") String name1, @RequestParam(required = false, defaultValue = "Paul Robinson") String name2) {
        return personService.findAgeDifferenceInDays(name1, name2);
    }

    @RequestMapping(value = "/personByGender", method = RequestMethod.GET, produces = "application/json")
    public long getPersonByGender(@RequestParam(defaultValue = "MALE") String gender) {
        return personService.findNumberOfPersonByGender(Gender.fromString(gender));
    }
}