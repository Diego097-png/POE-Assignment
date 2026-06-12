/**
 *
 * @author SsjTD
 */
// ChatApp
package com.chatapp;

import java.util.Scanner;

public class ChatApp {

    private static final MessageDataStore dataStore = new MessageDataStore();

    public static void main(String[] args) {
        Login loginSystem = new Login();
        boolean running = true;

        populateInstitutionalTestData();

        System.out.println("=== WELCOME TO CHATAPP (PROG5121 PoE BUILD) ===");

        try (Scanner input = new Scanner(System.in)) {
            while (running) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Access QuickChat Engine");
                System.out.println("4. View Stored Tasks Report (Part 3 Options)");
                System.out.println("5. Exit");
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
                        System.out.println("\n>>> " + loginSystem.registerUser(user, pass, phone, first, last));
                    }
                    case 2 -> {
                        System.out.print("Username: ");
                        String loginUser = input.nextLine();
                        System.out.print("Password: ");
                        String loginPass = input.nextLine();
                        if (loginSystem.loginUser(loginUser, loginPass)) {
                            System.out.println("\n>>> Login Status: " + loginSystem.returnLoginStatus(true));
                        } else {
                            System.out.println("\n>>> Login Status: " + loginSystem.returnLoginStatus(false));
                        }
                    }
                    case 3 -> runPart2MessagingMenu(input, loginSystem);
                    case 4 -> runPart3ReportingMenu(input);
                    case 5 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private static void populateInstitutionalTestData() {
        dataStore.addMessageWithFlag(new Message(1, "+27834557896", "Did you get the cake?"), "Sent");
        dataStore.addMessageWithFlag(new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time."), "Stored");
        dataStore.addMessageWithFlag(new Message(3, "+27834484567", "Yohoooo, I am at your gate."), "Disregard");
        
        Message msg4 = new Message(4, "0838884567", "It is dinner time !");
        dataStore.addMessageWithFlag(msg4, "Sent");
        
        dataStore.addMessageWithFlag(new Message(5, "+27838884567", "Ok, I am leaving without you."), "Stored");
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
        int messageCounter = 0;

        System.out.println("\n--- Capturing " + maxMessages + " Messages Live ---");
        while (messageCounter < maxMessages) {
            System.out.print("\nEnter Recipient Cell Number: ");
            String recipient = input.nextLine();
            System.out.print("Enter Message Content: ");
            String content = input.nextLine();

            Message tempMsg = new Message(messageCounter + 1, recipient, content);
            String lengthCheck = tempMsg.checkMessageLength(content);
            boolean isCellValid = loginSystem.checkCellPhoneNumber(recipient);

            if (lengthCheck.equals("Message ready to send.") && isCellValid) {
                // Instantly saves live inputs into your Part 3 data store logic engine
                dataStore.addMessageWithFlag(tempMsg, "Sent");
                messageCounter++;
                System.out.println(">>> Status: " + tempMsg.SentMessage(1));
            } else {
                System.out.println(">>> [!] Formatting error. Message rejected.");
                break;
            }
        }
    }

    private static void runPart3ReportingMenu(Scanner input) {
        System.out.println("\n--- Part 3: Store Data & Display Task Report ---");
        System.out.println("a. Display sender & recipient of stored messages");
        System.out.println("b. Display the longest stored message");
        System.out.println("c. Search for message ID");
        System.out.println("d. Search all messages for a particular recipient");
        System.out.println("e. Delete a message using hash parameter");
        System.out.println("f. Display full verification report");
        System.out.print("Select sub-option (a-f): ");
        
        String selection = input.nextLine().trim().toLowerCase();
        switch (selection) {
            case "a" -> System.out.println("\n" + dataStore.displayStoredSendersAndRecipients());
            case "b" -> System.out.println("\nLongest Stored Message: " + dataStore.getLongestStoredMessage());
            case "c" -> {
                System.out.print("Enter Target Message ID: ");
                String id = input.nextLine().trim();
                System.out.println("\nResult: " + dataStore.searchByMessageId(id));
            }
            case "d" -> {
                System.out.print("Enter Target Recipient Number: ");
                String rec = input.nextLine().trim();
                System.out.println("\nResult: " + dataStore.searchAllMessagesForRecipient(rec));
            }
            case "e" -> {
                System.out.print("Enter Target Message Hash Code: ");
                String hash = input.nextLine().trim();
                System.out.println("\nResult: " + dataStore.deleteMessageByHash(hash));
            }
            case "f" -> System.out.println("\n" + dataStore.generateFullReport());
            default -> System.out.println("Invalid reporting command interface action parameter.");
        }
    }
}