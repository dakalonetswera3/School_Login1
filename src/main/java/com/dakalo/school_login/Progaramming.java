  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dakalo.school_login;

import java.util.Scanner;

public class Progaramming {
//Main method where program execution begins
    public static void main(String[] args) {
        //Create Scaner object to read user input from the console 
        Scanner input = new Scanner(System.in);
        //Instantiate RegisterUser class to handle login and registration logic
        RegisterUser reg = new RegisterUser();
        
        //Boolean flag to control the main application loop
       // Instantiate Message object and load test data for Part 3
        Message msgObj = new Message();
        msgObj.loadTestData();

        boolean run = true;
        while (run) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Stored Messages (Part 3 Features)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                // Your existing Login feature
                System.out.print("Enter Username: ");
                String username = input.nextLine();
                System.out.print("Enter Password: ");
                String password = input.nextLine();
                boolean isLoggedIn = reg.loginUser(username, password);
                System.out.println(reg.returnLoginStatus(isLoggedIn));
            } 
            else if (choice.equals("2")) {
                // Your existing Register feature
                System.out.print("Enter First Name: ");
                String firstName = input.nextLine();
                System.out.print("Enter Last Name: ");
                String lastName = input.nextLine();
                System.out.print("Enter Username: ");
                String username = input.nextLine();
                System.out.print("Enter Password: ");
                String password = input.nextLine();
                System.out.print("Enter Cell Phone Number: ");
                String cellNumber = input.nextLine();
                String result = reg.registerUser(username, password, cellNumber, firstName, lastName);
                System.out.println(result);
            } 
            else if (choice.equals("3")) {
                // 4th Main Menu Option for Stored Messages (Part 3)
                boolean subRun = true;
                while (subRun) {
                    System.out.println("\n--- STORED MESSAGES MENU ---");
                    System.out.println("a. Display sender and recipient of all stored messages");
                    System.out.println("b. Display the longest stored message");
                    System.out.println("c. Search for a message ID");
                    System.out.println("d. Search messages by recipient");
                    System.out.println("e. Delete a message using message hash");
                    System.out.println("f. Display full message report");
                    System.out.println("g. Return to Main Menu");
                    System.out.print("Choose an option (a-g): ");
                    String subChoice = input.nextLine().toLowerCase();

                    switch (subChoice) {
                        case "a":
                            System.out.println(msgObj.displayStoredSendersAndRecipients());
                            break;
                        case "b":
                            System.out.println("Longest Message: " + msgObj.displayLongestMessage());
                            break;
                        case "c":
                            System.out.print("Enter Message ID to search (e.g., ID002): ");
                            String searchID = input.nextLine();
                            System.out.println(msgObj.searchByMessageID(searchID));
                            break;
                        case "d":
                            System.out.print("Enter Recipient cell number: ");
                            String searchRecip = input.nextLine();
                            System.out.println(msgObj.searchByRecipient(searchRecip));
                            break;
                        case "e":
                            System.out.print("Enter Message Hash to delete: ");
                            String hashDel = input.nextLine();
                            if (msgObj.deleteMessageByHash(hashDel)) {
                                System.out.println("Message successfully deleted.");
                            } else {
                                System.out.println("Message hash not found.");
                            }
                            break;
                        case "f":
                            System.out.println(msgObj.displayMessageReport());
                            break;
                        case "g":
                            subRun = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } 
            else if (choice.equals("4")) {
                System.out.println("Exiting program. Goodbye!");
                run = false;
            } 
            else {
                System.out.println("Invalid option, please choose 1, 2, 3, or 4.");
            }
        }
        input.close();
    }
}