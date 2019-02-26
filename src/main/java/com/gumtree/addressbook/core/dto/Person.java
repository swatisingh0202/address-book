package com.gumtree.addressbook.core.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Person {

    private final Date dateOfBirth;
    private final String name;
    private final Gender gender;

    private Person(Builder builder) {
        this.dateOfBirth = builder.dateOfBirth;
        this.name = builder.name;
        this.gender = builder.gender;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public Gender getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(dateOfBirth, person.dateOfBirth) &&
                Objects.equals(name, person.name) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, name, gender);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private Gender gender;
        private Date dateOfBirth;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

}
