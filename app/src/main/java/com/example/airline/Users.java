package com.example.airline;
import java.util.ArrayList;
import java.util.List;

public class Users {

    private static Users instance;
    private List<Customer> customerList;
    private List<Staff> staffList;
    private List<Admin> adminList;

    // Private constructor to prevent instantiation from outside
    private Users() {
        customerList = new ArrayList<>();
        staffList = new ArrayList<>();
        adminList = new ArrayList<>();
    }

    // Method to get the singleton instance
    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }


    // Method to add a new user
    public void addUser(String username, String password, String email, String phone, boolean isLoggedIn, UserEnum userEnum) {
        if (userEnum == UserEnum.ADMIN){
            adminList.add(new Admin(username, password, email, phone, isLoggedIn));
        }else if (UserEnum.CUSTOMER == userEnum){
            customerList.add(new Customer(username, password, email, phone, isLoggedIn));
        }else if (UserEnum.STAFF == userEnum){
            staffList.add(new Staff(username, password, email, phone, isLoggedIn));
        }
    }

    // Method to retrieve a user by username
    public Admin getAdminByUsername(String username){
        for (Admin admin : adminList){
            if(admin.getUsername().equals(username)){
                return admin;
            }
        }
        return null;
    }

    public Staff getStaffByUsername(String username){
        for (Staff staff : staffList){
            if(staff.getUsername().equals(username)){
                return staff;
            }
        }
        return null;
    }

    public Customer getCustomerByUsername(String username){
            for (Customer customer : customerList){
            if(customer.getUsername().equals(username)){
                return customer;
            }
        }
        return null;
    }

    public User getLoggedInUser(){
        for (User user : customerList){
            if (user.getLoggedIn()){
                return user;
            }
        }
        for (User user : staffList){
            if (user.getLoggedIn()){
                return user;
            }
        }
        for (User user : adminList){
            if (user.getLoggedIn()){
                return user;
            }
        }
        return null;
    }



    //TODO authenticate
    // Method to authenticate a user
//    public boolean authenticateUser(String username, String password) {
//        com.example.myapplication.User user = getUserByUsername(username);
//        return (user != null) && user.authenticate(username, password);
//    }
//
//    // Method to check if a username is already taken
//    public boolean isUsernameTaken(String username) {
//        for (com.example.myapplication.User user : userList) {
//            if (user.getUsername().equals(username)) {
//                return true;
//            }
//        }
//        return false; // Username not taken
//    }
//

}
