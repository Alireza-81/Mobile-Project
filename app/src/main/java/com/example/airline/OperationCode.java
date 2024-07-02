package com.example.airline;

public enum OperationCode {
    SUCCESS("success"),
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
