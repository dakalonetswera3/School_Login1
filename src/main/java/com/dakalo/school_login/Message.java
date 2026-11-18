/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dakalo.school_login;

public class Message {

    // 1. Validates that the Message ID is not more than 10 characters
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    // 2. Validates that the recipient cell number is no more than 10 characters and starts with an international code
    public boolean checkRecipientCell(String cellNumber) {
        return cellNumber != null && cellNumber.matches("^\\+\\d{1,9}$") && cellNumber.length() <= 10;
    }
    // 3. Creates and returns the Message Hash in uppercase
    public String createMessageHash(String messageID, int messageNum, String messageText) {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        String prefix = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        
        String hash = prefix + ":" + messageNum + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // 4. Handles sending, storing, or deleting user choices
    public String sentMessage(String choice) {
        switch (choice) {
            case "1":
                return "Message successfully sent.";
            case "0":
                return "Press 0 to delete the message.";
            case "2":
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }
    // 5. Stores the message details into a JSON file
    public void storeMessage(String messageID, String recipient, String messageText, String messageHash) {
        String jsonContent = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"recipient\": \"" + recipient + "\",\n" +
                "  \"message\": \"" + messageText + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\"\n" +
                "}";
        try (java.io.FileWriter file = new java.io.FileWriter("messages.json", true)) {
            file.write(jsonContent + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // 6. Returns a confirmation or prints message tracking details
    public String printMessages(String messageID, String messageHash, String recipient, String messageText) {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    // 7. Returns the total number of messages processed
    public int returnTotalMessages(int total) {
        return total;
    }
// Parallel arrays / lists for Part 3 data management (Test Data & Stored Messages)
    public java.util.ArrayList<String> sentMessages = new java.util.ArrayList<>();
    public java.util.ArrayList<String> disregardedMessages = new java.util.ArrayList<>();
    public java.util.ArrayList<String> storedMessages = new java.util.ArrayList<>();
    public java.util.ArrayList<String> messageHashes = new java.util.ArrayList<>();
    public java.util.ArrayList<String> messageIDs = new java.util.ArrayList<>();
    public java.util.ArrayList<String> recipients = new java.util.ArrayList<>();

    // Method to initialize the 5 test data messages required by the POE rubric
    public void loadTestData() {
        // Message 1 (Sent)
        recipients.add("+27834557896");
        storedMessages.add("Did you get the cake?");
        messageIDs.add("ID001");
        messageHashes.add("ID:1:DIDCAKE");
        sentMessages.add("Did you get the cake?");

        // Message 2 (Stored)
        recipients.add("+27838884567");
        storedMessages.add("Where are you? You are late! I have asked you to be on time.");
        messageIDs.add("ID002");
        messageHashes.add("ID:2:WHERETIME");

        // Message 3 (Disregarded)
        recipients.add("+27834484567");
        storedMessages.add("Yohoooo, I am at your gate.");
        messageIDs.add("ID003");
        messageHashes.add("ID:3:YOHOOGATE");
        disregardedMessages.add("Yohoooo, I am at your gate.");

        // Message 4 (Sent / Developer)
        recipients.add("0838884567");
        storedMessages.add("It is dinner time !");
        messageIDs.add("ID004");
        messageHashes.add("ID:4:ITSDINNER");
        sentMessages.add("It is dinner time !");

        // Message 5 (Stored)
        recipients.add("+27838884567");
        storedMessages.add("Ok, I am leaving without you.");
        messageIDs.add("ID005");
        messageHashes.add("ID:5:OKLEAVING");
    }

    // a. Display sender and recipient of all stored messages
    public String displayStoredSendersAndRecipients() {
        StringBuilder sb = new StringBuilder("--- Stored Messages Senders & Recipients ---\n");
        for (int i = 0; i < storedMessages.size(); i++) {
            sb.append("Recipient: ").append(recipients.get(i))
              .append(" | Message: ").append(storedMessages.get(i)).append("\n");
        }
        return sb.toString();
    }

    // b. Display the longest stored message
    public String displayLongestMessage() {
        if (storedMessages.isEmpty()) return "No stored messages available.";
        String longest = storedMessages.get(0);
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    // c. Search for a message ID and display corresponding recipient and message
    public String searchByMessageID(String searchID) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equalsIgnoreCase(searchID)) {
                return "Recipient: " + recipients.get(i) + " | Message: " + storedMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

    // d. Search for all messages sent or stored for a particular recipient
    public String searchByRecipient(String searchRecipient) {
        StringBuilder sb = new StringBuilder("--- Messages for " + searchRecipient + " ---\n");
        boolean found = false;
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(searchRecipient)) {
                sb.append("- ").append(storedMessages.get(i)).append("\n");
                found = true;
            }
        }
        if (!found) return "No messages found for this recipient.";
        return sb.toString();
    }

    // e. Delete a message using the message hash
    public boolean deleteMessageByHash(String targetHash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(targetHash)) {
                storedMessages.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);
                messageHashes.remove(i);
                return true;
            }
        }
        return false;
    }

    // f. Display a report listing full details of all stored messages
    public String displayMessageReport() {
        StringBuilder sb = new StringBuilder("============= FULL MESSAGE REPORT =============");
        for (int i = 0; i < storedMessages.size(); i++) {
            sb.append("\nMessage Hash: ").append(messageHashes.get(i))
              .append("\nRecipient: ").append(recipients.get(i))
              .append("\nMessage: ").append(storedMessages.get(i))
              .append("\n--------------------------------------------");
        }
        return sb.toString();
    }
}
