package com.example.airline;

public class User {
    private String username;
    private String password;
    private boolean isLoggedIn;
    // Constructor
    public User(String username, String password, boolean isLoggedIn) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to check if the provided username and password match the stored values
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

}
