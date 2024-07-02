package com.example.airline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Flight {
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private CityEnum origin;
    private CityEnum destination;
    private LocalDateTime dateTime;
    private String airplaneNameId;
    private List<String> staffList;
    private List<String> customerList;
    private int remainingCapacity;
    private int price;



    public Flight(CityEnum origin, CityEnum destination, LocalDateTime dataTime, String airplaneNameId, List<String> staffList, List<String> customerList, int remainingCapacity, int price) {
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dataTime;
        this.airplaneNameId = airplaneNameId;
        this.staffList = staffList;
        this.customerList = customerList;
        this.remainingCapacity = remainingCapacity;
        this.price = price;
    }

    public void decreaseRemainingCapacity(int value){
        this.remainingCapacity -= value;
    }
    public void increaseRemainingCapacity(int value){
        this.remainingCapacity += value;
    }
    public void setOrigin(CityEnum origin) {
        this.origin = origin;
    }

    public void setDestination(CityEnum destination) {
        this.destination = destination;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setAirplane(String airplaneNameId) {
        this.airplaneNameId = airplaneNameId;
    }


    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CityEnum getOrigin() {
        return origin;
    }

    public CityEnum getDestination() {
        return destination;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getAirplaneNameId() {
        return airplaneNameId;
    }

    public List<String> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<String> staffList) {
        this.staffList = staffList;
    }

    public List<String> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<String> customerList) {
        this.customerList = customerList;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public int getPrice() {
        return price;
    }
}
