package com.example.airline;

public enum UserEnum {
    STAFF("staff"),
    CUSTOMER("customer"),
    ADMIN("admin");

    private final String value;

    UserEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
