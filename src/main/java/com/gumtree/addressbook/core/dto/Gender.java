package com.gumtree.addressbook.core.dto;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender fromString(String name) {
        if (name != null) {
            for (Gender gender : values())
                if (gender.name.equalsIgnoreCase(name)) {
                    return gender;
                }
        }
        throw new IllegalArgumentException("Can not resolve the passed name parameter ");
    }

    public String getName() {
        return name;
    }
}
