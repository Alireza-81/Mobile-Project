package com.example.airline;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;

    private ArrayList<Flight> purchased;
    private boolean isLoggedIn;
    // Constructor
    public User(String username, String password, String email, String phone, boolean isLoggedIn) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isLoggedIn = isLoggedIn;
        this.purchased = new ArrayList<Flight>();
    }

    public boolean getLoggedIn(){
        return this.isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Flight> getPurchased() {
        return this.purchased;
    }

    public void addFlight(Flight flight){
        this.purchased.add(flight);
    }

    // Method to check if the provided username and password match the stored values
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

}
