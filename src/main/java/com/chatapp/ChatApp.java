/**
 *
 * @author SsjTD
 */
package com.chatapp;

import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
Login login = new Login();

// First Name
System.out.print("Enter first name: ");
String firstName = scanner.nextLine();

// Last Name
System.out.print("Enter last name: ");
String lastName = scanner.nextLine();

// USERNAME
String username;
while (true) {
    System.out.print("Enter username: ");
    username = scanner.nextLine();

    if (login.checkUserName(username)) {
        System.out.println("Username successfully captured.");
        break;
    } else {
        System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
    }
}

// PASSWORD
String password;
while (true) {
    System.out.print("Enter password: ");
    password = scanner.nextLine();

    if (login.checkPasswordComplexity(password)) {
        System.out.println("Password successfully captured.");
        break;
    } else {
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }
}

// PHONE
String phone;
while (true) {
    System.out.print("Enter phone number (+27...): ");
    phone = scanner.nextLine();

    if (login.checkCellPhoneNumber(phone)) {
        System.out.println("Cell phone number successfully added.");
        break;
    } else {
        System.out.println("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.");
    }
}

// FINAL REGISTRATION
String result = login.registerUser(username, password, phone, firstName, lastName);
System.out.println(result);
    
// LOGIN PHASE
System.out.print("Login - Enter username: ");
String loginUsername = scanner.nextLine();

System.out.print("Login - Enter password: ");
String loginPassword = scanner.nextLine();

boolean success = login.loginUser(loginUsername, loginPassword);

String loginMessage = login.returnLoginStatus(success);
System.out.println(loginMessage);
    }
}

