package com.example.airline;

public class Airplane {
    private String name_id;
    private String model;
    private int capacity;
    private int maxLuggageWeightPerPerson;

    public Airplane(String name_id, String model, int capacity, int maxLuggageWeightPerPerson) {
        this.name_id = name_id;
        this.model = model;
        this.capacity = capacity;
        this.maxLuggageWeightPerPerson = maxLuggageWeightPerPerson;
    }

    public void setName(String name) {
        this.name_id = name;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setMaxLuggageWeightPerPerson(int maxLuggageWeightPerPerson) {
        this.maxLuggageWeightPerPerson = maxLuggageWeightPerPerson;
    }

    public String getName() {
        return name_id;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxLuggageWeightPerPerson() {
        return maxLuggageWeightPerPerson;
    }
}
