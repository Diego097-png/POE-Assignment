/**
 *
 * @author SsjTD
 */
package com.chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        Login loginSystem = new Login();
        boolean running = true;

        System.out.println("=== WELCOME TO CHATAPP ===");

        try (Scanner input = new Scanner(System.in)) {
            
            while (running) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Username: ");
                        String user = input.nextLine();
                        System.out.print("Enter Password: ");
                        String pass = input.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = input.nextLine();
                        System.out.print("Enter First Name: ");
                        String first = input.nextLine();
                        System.out.print("Enter Last Name: ");
                        String last = input.nextLine();

                        String regStatus = loginSystem.registerUser(user, pass, phone, first, last);
                        System.out.println("\n>>> " + regStatus);
                    }
                    case 2 -> {
                        System.out.print("Username: ");
                        String loginUser = input.nextLine();
                        System.out.print("Password: ");
                        String loginPass = input.nextLine();

                        if (loginSystem.loginUser(loginUser, loginPass)) {
                            System.out.println("\n>>> Login Status: " + loginSystem.returnLoginStatus(true));
                            runPart2MessagingMenu(input, loginSystem);
                        } else {
                            System.out.println("\n>>> Login Status: " + loginSystem.returnLoginStatus(false));
                        }
                    }
                    case 3 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        } 
    }

    private static void runPart2MessagingMenu(Scanner input, Login loginSystem) {
        System.out.println("\n==================================");
        
        if (loginSystem.getRegisteredUser() != null) {
            String firstName = loginSystem.getRegisteredUser().getFirstName();
            String lastName = loginSystem.getRegisteredUser().getLastName();
            System.out.println("Welcome to QuickChat, " + firstName + " " + lastName + ".");
        } else {
            System.out.println("Welcome to QuickChat.");
        }
        
        System.out.println("==================================");

        System.out.print("How many messages would you like to capture this session? ");
        int maxMessages = Integer.parseInt(input.nextLine());

        Message[] messageArchive = new Message[maxMessages];
        int messageCounter = 0;
        boolean inSession = true;

        while (inSession) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1 -> {
                    if (messageCounter >= maxMessages) {
                        System.out.println("\n>>> Limit reached! You have already entered your maximum of " + maxMessages + " messages.");
                        break;
                    }

                    System.out.println("\n--- Capture Messages (" + (maxMessages - messageCounter) + " slots remaining) ---");

                    while (messageCounter < maxMessages) {
                        System.out.print("Enter Recipient Cell Number (e.g. +27838968976): ");
                        String recipient = input.nextLine();

                        System.out.print("Enter Message Content (Max 250 chars): ");
                        String content = input.nextLine();

                        Message tempMsg = new Message(messageCounter + 1, recipient, content);

                        String lengthCheck = tempMsg.checkMessageLength(content);
                        System.out.println(">>> Length validation: " + lengthCheck);

                        boolean isCellValid = loginSystem.checkCellPhoneNumber(recipient);
                        String cellCheck = isCellValid ? "Cell phone number successfully added." : "Cell phone number incorrectly formatted or does not contain international code.";
                        System.out.println(">>> Recipient validation: " + cellCheck);

                        if (lengthCheck.equals("Message ready to send.") && isCellValid) {
                            messageArchive[messageCounter] = tempMsg;
                            messageCounter++;
                            System.out.println(">>> Action status: " + tempMsg.SentMessage(1));
                        } else {
                            System.out.println(">>> [!] Message rejected due to formatting errors. Please try adding it again.");
                            break; 
                        }

                        if (messageCounter < maxMessages) {
                            System.out.print("Do you want to enter the next message now? (yes/no): ");
                            String answer = input.nextLine();
                            if (answer.equalsIgnoreCase("no")) {
                                break; 
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Recently Sent Messages ---");
                    boolean hasMessages = false;
                    
                    for (int i = 0; i < messageCounter; i++) {
                        if (messageArchive[i] != null) {
                            System.out.println("\n[Slot #" + (i + 1) + "]");
                            System.out.println(messageArchive[i].printMessages());
                            System.out.println("-------------------------");
                            hasMessages = true;
                        }
                    }
                    
                    if (!hasMessages) {
                        System.out.println(">>> No messages sent during this session yet.");
                    }
                }
                case 3 -> {
                    System.out.println("\nExiting QuickChat session... Returning to Main Login Screen.");
                    inSession = false;
                }
                default -> System.out.println("\n>>> Invalid option. Please select 1, 2, or 3.");
            }
        }
    }
}