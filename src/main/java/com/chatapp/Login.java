package com.chatapp;

public class Login {

    private User registeredUser;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.matches("^\\+\\d{10,13}$");
    }

    public String registerUser(String username, String password, String phone,
                               String firstName, String lastName) {

        registeredUser = new User(username, password, phone, firstName, lastName);
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        if (registeredUser == null) return false;

        return registeredUser.getUsername().equals(username) &&
               registeredUser.getPassword().equals(password);
    }

    public String returnLoginStatus(boolean success) {
        return success ? "true" : "false";
    }
}