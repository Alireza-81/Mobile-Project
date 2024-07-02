package com.example.airline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flights {
    private List<Flight> flightList;
    private static Flights instance;
    private Flights() {
        flightList = new ArrayList<>();
    }

    // Method to get the singleton instance
    public static Flights getInstance() {
        if (instance == null) {
            instance = new Flights();
        }
        return instance;
    }

    // for 2 way flights, call this function twice and show the results in two columns,
    // one for departure and another for arrival
    public List<Flight> getOneWayFlightByDateOriginDestination(CityEnum origin, CityEnum destination, LocalDateTime dateTime){
        return getFlightByDate(dateTime, getFlightByOriginDestination(origin, destination, flightList)); 
    }
    private List<Flight> getFlightsWithEnoughCapacity(int minCapacity, List<Flight> flightList){
        List<Flight> res = new ArrayList<>();
        for (Flight flight : flightList){
            if(flight.getRemainingCapacity() > minCapacity){
                res.add(flight);
            }
        }
        return res;
    }
    private List<Flight> getFlightByOriginDestination(CityEnum origin, CityEnum destination, List<Flight> flightList){
        List<Flight> res = new ArrayList<>();
        for (Flight flight : flightList){
            if(flight.getOrigin() == origin && flight.getDestination() == destination){
                res.add(flight);
            }
        }
        return res;
    }

    private List<Flight> getFlightByDate(LocalDateTime dateTime, List<Flight> flightList){
        List<Flight> res = new ArrayList<>();
        for (Flight flight : flightList){
            if(flight.getDateTime().getYear() == dateTime.getYear()
                && flight.getDateTime().getMonth() == dateTime.getMonth()
                && flight.getDateTime().getDayOfMonth() == dateTime.getDayOfMonth()){
                res.add(flight);
            }

        }
        return res;
    }

    public OperationCode increaseFlightPassengers(Flight flight, int value){
        if(value <= flight.getRemainingCapacity()){
            flight.decreaseRemainingCapacity(value);
            return OperationCode.SUCCESS;
        }
        return OperationCode.WRONG_VALUE_CAPACITY;
    }
    public OperationCode decreaseFlightPassengers(Flight flight, int value){
        if(flight.getAirplane().getCapacity() - flight.getRemainingCapacity() > value){
            flight.increaseRemainingCapacity(value);
            return OperationCode.SUCCESS;
        }
        return OperationCode.WRONG_VALUE_CAPACITY;
    }

    private List<Flight> getCustomerPreviousFlights(Customer customer){
        List<Flight> res = new ArrayList<>();
        for (Flight flight : flightList){
            if(flight.getDateTime().getYear() == dateTime.getYear()
                    && flight.getDateTime().getMonth() == dateTime.getMonth()
                    && flight.getDateTime().getDayOfMonth() == dateTime.getDayOfMonth()){
                res.add(flight);
            }

        }
        return res;
    }

}
