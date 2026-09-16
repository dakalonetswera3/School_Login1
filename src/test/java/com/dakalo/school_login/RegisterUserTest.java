/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dakalo.school_login;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterUserTest {

    private final RegisterUser user = new RegisterUser();

    @Test
    public void testRegisterUserValidUsername() {
        String result = user.registerUser("kyl_1", "Ch&&&sec@ke991", "+27838968976", "John", "Doe");
        assertEquals("Username successfully captured.", result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        String result = user.registerUser("kyl!!!!!!!", "Ch&&&sec@ke991", "+27838968976", "John", "Doe");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserValidPassword() {
        String result = user.registerUser("kyl_1", "Ch&&&sec@ke991", "+27838968976", "John", "Doe");
        assertEquals("Password successfully captured.", result);
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        String result = user.registerUser("kyl_1", "password", "+27838968976", "John", "Doe");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterUserValidCellPhone() {
        String result = user.registerUser("kyl_1", "Ch&&&sec@ke991", "+27838968976", "John", "Doe");
        assertEquals("Cell number successfully captured.", result);
    }

    @Test
    public void testRegisterUserInvalidCellPhone() {
        String result = user.registerUser("kyl_1", "Ch&&&sec@ke991", "08966553", "John", "Doe");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }

    @Test
    public void testCheckUserNameTrue() {
        assertTrue(user.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameFalse() {
        assertFalse(user.checkUserName("kyl!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityTrue() {
        assertTrue(user.checkPasswordComplexity("Ch&&&sec@ke991"));
    }

    @Test
    public void testCheckPasswordComplexityFalse() {
        assertFalse(user.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberTrue() {
        assertTrue(user.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberFalse() {
        assertFalse(user.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginUserSuccess() {
        user.registerUser("kyl_1", "Ch&&&sec@ke991", "+27838968976", "John", "Doe");
        assertTrue(user.loginUser("kyl_1", "Ch&&&sec@ke991"));
    }

    @Test
    public void testLoginUserFailure() {
        assertFalse(user.loginUser("kyl_1", "WrongPassword1!"));
    }
}
