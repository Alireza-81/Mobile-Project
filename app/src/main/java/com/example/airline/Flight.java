package com.example.airline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Flight {
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private CityEnum origin;
    private CityEnum destination;
    private LocalDateTime dateTime;
    private Airplane airplane;
    private List<Staff> staffList;
    private List<Customer> customerList;
    private int remainingCapacity;
    private int price;



    public Flight(CityEnum origin, CityEnum destination, LocalDateTime dataTime, Airplane airplane, List<Staff> staffList, List<Customer> customerList, int remainingCapacity, int price) {
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dataTime;
        this.airplane = airplane;
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

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
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

    public Airplane getAirplane() {
        return airplane;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public int getPrice() {
        return price;
    }
}
