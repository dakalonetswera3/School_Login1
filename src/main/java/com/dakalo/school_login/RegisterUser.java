/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dakalo.school_login;

public class RegisterUser {

    // Member variables to store user data in memory
    private String storeUser = "";
    private String storePass = "";
    private String storeCell = "";
    private String storeFirstName = "";
    private String storeLastName = "";

    // Method 1: Check Username
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Method 2: Check Password Complexity
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasCapital = true;
            else if (Character.isDigit(ch)) hasNumber = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // Method 3: Check Cell Phone Number (+27...)
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        return cellNumber.matches("^\\+27\\d{9}$");
    }

    // Method 4: Register User and store the details
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // Saving inputs into your class storage variables
        this.storeUser = username;
        this.storePass = password;
        this.storeCell = cellNumber;
        this.storeFirstName = firstName;
        this.storeLastName = lastName;

        return "Password successfully captured.";
    }

    // Method 5: Check login details against stored values
    public boolean loginUser(String username, String password) {
        return username != null && password != null 
                && username.equals(this.storeUser) 
                && password.equals(this.storePass);
    }

    // Method 6: Return login message
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + storeFirstName + ", " + storeLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
   
   
   

