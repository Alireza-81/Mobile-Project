package com.example.airline;

import java.util.ArrayList;
import java.util.List;

public class Airplanes {
    private List<Airplane> airplaneList;
    private static Airplanes instance;

    public Airplanes() {
        this.airplaneList = new ArrayList<>();
    }
    public static Airplanes getInstance(){
        if (instance == null){
            instance = new Airplanes();
        }
        return instance;
    }

    public boolean addAirplane(String name_id, String model, Flight flight, int capacity, int maxLuggageWeight){
        if (isNameUnique(name_id)){
            airplaneList.add(new Airplane(name_id, model, flight, capacity, maxLuggageWeight));
            return true;
        }
        return false;
    }
    public boolean isNameUnique(String name_id){
        for (Airplane airplane : airplaneList){
            if (airplane.getName().equals(name_id)){
                return false;
            }
        }
        return true;
    }

    public boolean removeAirplane(String name_id){
        for (Airplane airplane : airplaneList){
            if (airplane.getName().equals(name_id)){
                airplaneList.remove(airplane);
                return true;
            }
        }
        return false;
    }
    public boolean editAirplane(String name_id, String model, Flight flight, int cap, int maxW){
        for (Airplane airplane : airplaneList){
            if (airplane.getName().equals(name_id)){
                airplane.setName(name_id);
                airplane.setCapacity(cap);
                airplane.setFlight(flight);
                airplane.setModel(model);
                airplane.setMaxLuggageWeightPerPerson(maxW);
                return true;
            }
        }
        return false;
    }
}
