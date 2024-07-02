package com.example.airline;

import java.util.ArrayList;
import java.util.List;

public enum CityEnum {
    TEHRAN("tehran"),
    SHIRAZ("shiraz"),
    ISFAHAN("isfahan"),
    MASHHAD("mashhad"),
    AHVAZ("ahvaz"),
    TABRIZ("tabriz"),
    KERMAN("kerman"),
    KISH("kish"),
    BANDARABBAS("bandar abbas");
    private final String value;
    CityEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public static List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (CityEnum city : CityEnum.values()) {
            values.add(city.getValue());
        }
        return values;
    }
}
