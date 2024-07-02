package com.example.airline;

public class Airplane {
    private String name_id;
    private String model;
    private Flight flight; //null if not assigned to any flights
    private int capacity;
    private int maxLuggageWeightPerPerson;

    // TODO add state: healthy, (under maintenance, broken) -> unavailable

    public Airplane(String name_id, String model, Flight flight, int capacity, int maxLuggageWeightPerPerson) {
        this.name_id = name_id;
        this.model = model;
        this.flight = flight;
        this.capacity = capacity;
        this.maxLuggageWeightPerPerson = maxLuggageWeightPerPerson;
    }

    public void setName(String name) {
        this.name_id = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    public Flight getFlight() {
        return flight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxLuggageWeightPerPerson() {
        return maxLuggageWeightPerPerson;
    }
}
