package com.example.airline;

public enum OperationCode {
    SUCCESS("success"),
    FAILED_INSERTION("failed to insert!"),
    AIRPLANE_NAME_REPEATED("this name is used before!"),
    INVALID("invalid command"),
    WRONG_VALUE_CAPACITY("wrong value for changing flight capacity!");

    private final String value;

    OperationCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
