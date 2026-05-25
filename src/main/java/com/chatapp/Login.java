package com.chatapp;

import java.util.regex.Pattern;

public class Login {

    private User registeredUser;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*()_+-=[]{}|;':\",./<>?".indexOf(c) != -1) hasSpecial = true;
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        return Pattern.matches("^\\+27\\d{9}$", phone);
    }

    public String registerUser(String username, String password, String phone, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(phone)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        this.registeredUser = new User(username, password, phone, firstName, lastName);
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    public boolean loginUser(String username, String password) {
        if (this.registeredUser == null) {
            return false;
        }
        return this.registeredUser.getUsername().equals(username) && 
               this.registeredUser.getPassword().equals(password);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + this.registeredUser.getFirstName() + " ," + this.registeredUser.getLastName() + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public User getRegisteredUser() {
        return this.registeredUser;
    }
}