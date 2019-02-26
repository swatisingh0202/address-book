package com.gumtree.addressbook.core.dto;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public static Gender fromString(String gender) {
        if (gender != null) {
            for (Gender genderName : values())
                if (genderName.gender.equalsIgnoreCase(gender)) {
                    return genderName;
                }
        }
        throw new IllegalArgumentException("Can not resolve the passed gender parameter ");
    }

    public String getGender() {
        return gender;
    }
}
