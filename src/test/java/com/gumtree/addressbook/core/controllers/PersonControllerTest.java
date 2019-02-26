package com.gumtree.addressbook.core.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPersons() throws Exception {
        this.mvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().string(getResult()));
    }

    @Test
    public void getOldestPerson() throws Exception {
        this.mvc.perform(get("/person/oldest"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"dateOfBirth\":\"1974-08-13T23:00:00.000+0000\",\"name\":\"Wes Jackson\",\"gender\":\"MALE\"}"));
    }

    @Test
    public void findAgeDifferenceInDaysDefault() throws Exception {
        this.mvc.perform(get("/person/ageDifference"))
                .andExpect(status().isOk())
                .andExpect(content().string("2862"));
    }

    @Test
    public void getPersonByGenderMale() throws Exception {
        this.mvc.perform(get("/person/personByGender"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    public void getPersonByGenderFemale() throws Exception {
        this.mvc.perform(get("/person/personByGender").
                param("gender", "FEMALE"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    private String getResult() {
        return "[{\"dateOfBirth\":\"1977-03-16T00:00:00.000+0000\",\"name\":\"Bill McKnight\",\"gender\":\"MALE\"}," +
                "{\"dateOfBirth\":\"1985-01-15T00:00:00.000+0000\",\"name\":\"Paul Robinson\",\"gender\":\"MALE\"}," +
                "{\"dateOfBirth\":\"1991-11-20T00:00:00.000+0000\",\"name\":\"Gemma Lane\",\"gender\":\"FEMALE\"}," +
                "{\"dateOfBirth\":\"1980-09-19T23:00:00.000+0000\",\"name\":\"Sarah Stone\",\"gender\":\"FEMALE\"}," +
                "{\"dateOfBirth\":\"1974-08-13T23:00:00.000+0000\",\"name\":\"Wes Jackson\",\"gender\":\"MALE\"}]";
    }
}