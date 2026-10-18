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
        boolean run = true;
        
        // Main menu loop that keeps running until the user chooses to exit
        while (run) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = input.nextLine();
            
            // Option 1: Handle user login
            if (choice.equals("1")) {
                // LOGIN FEATURE
                System.out.print("Enter Username: ");
                String username = input.nextLine();

                System.out.print("Enter Password: ");
                String password = input.nextLine();
                
                // check credentials and print status message
                boolean isLoggedIn = reg.loginUser(username, password);
                String statusMessage = reg.returnLoginStatus(isLoggedIn);
                System.out.println(statusMessage);
                
                // QUICKCHAT INTEGRATION (Only runs if login is successful)
            if (isLoggedIn) {
                System.out.println("Welcome to QuickChat.");
                Message msgObj = new Message();
                boolean chatRunning = true;

                while (chatRunning) {
                    System.out.println("\n--- QUICKCHAT MENU ---");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");
                    System.out.print("Choose an option: ");
                    
                    String chatChoice = input.nextLine();
                    
                    if (chatChoice.equals("1")) {
                        System.out.print("How many messages would you like to send? ");
                        int totalMessages = Integer.parseInt(input.nextLine());
                        
                        for (int i = 1; i <= totalMessages; i++) {
                            System.out.print("Enter Message ID (max 10 chars): ");
                            String msgID = input.nextLine();
                            while (!msgObj.checkMessageID(msgID)) {
                                System.out.print("Invalid ID. Re-enter Message ID: ");
                                msgID = input.nextLine();
                            }
                            
                            System.out.print("Enter Recipient Cell Number (starts with +, max 10 chars): ");
                            String cellNum = input.nextLine();
                            while (!msgObj.checkRecipientCell(cellNum)) {
                                System.out.print("Invalid format. Re-enter Cell Number: ");
                                cellNum = input.nextLine();
                            }
                            
                            System.out.print("Enter Message Text (max 250 characters): ");
                            String text = input.nextLine();
                            while (text.length() > 250) {
                                System.out.print("Too long. Re-enter Message Text: ");
                                text = input.nextLine();
                            }
                            
                            String hash = msgObj.createMessageHash(msgID, i, text);
                            System.out.println("Message Hash: " + hash);
                            
                            System.out.println("Enter choice: 1) Send | 0) Delete | 2) Store");
                            String action = input.nextLine();
                            System.out.println(msgObj.sentMessage(action));
                            
                            if (action.equals("2")) {
                                msgObj.storeMessage(msgID, cellNum, text, hash);
                            }
                        }
                    } else if (chatChoice.equals("2")) {
                        System.out.println("Coming Soon.");
                    } else if (chatChoice.equals("3")) {
                        chatRunning = false;
                        System.out.println("Exiting QuickChat.");
                    } else {
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    }
                }
            }
                
                // Option 2: Handle new registration
            } else if (choice.equals("2")) {
                // REGISTRATION FEATURE
                System.out.print("Enter First Name: ");
                String firstName = input.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = input.nextLine();

                System.out.print("Enter Username: ");
                String username = input.nextLine();

                System.out.print("Enter Password: ");
                String password = input.nextLine();

                System.out.print("Enter Cell Phone Number (e.g. +27838968976): ");
                String cellNumber = input.nextLine();

                // Send captured inputs to RegisterUser class
                String result = reg.registerUser(username, password, cellNumber, firstName, lastName);
                System.out.println(result);
                
                // Option3 : Exit the program
            } else if (choice.equals("3")) {
                // EXIT FEATURE
                System.out.println("Exiting program. Goodbye!");
                run = false;
                // Handle invalid menu selections
            } else {
                System.out.println("Invalid option, please choose 1, 2, or 3.");
            }
        }

        input.close();
    }
}