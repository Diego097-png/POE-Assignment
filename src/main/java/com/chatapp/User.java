package com.chatapp;

public class User {
    private final String username;
    private final String password;
    private final String phone;
    private final String firstName;
    private final String lastName;

    public User(String username, String password, String phone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}