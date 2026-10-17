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
}
