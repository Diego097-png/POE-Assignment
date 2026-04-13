package com.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    Login login = new Login();

    // Username Tests
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("ab_c"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("abcde")); // no underscore
    }

    // Password Tests
    @Test
    public void testPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }

    @Test
    public void testPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // Phone Tests
    @Test
    public void testPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // Login Tests
    @Test
    public void testLoginUser_Success() {
        login.registerUser("ab_c", "Pass@123", "+27838968976", "John", "Doe");
        assertTrue(login.loginUser("ab_c", "Pass@123"));
    }

    @Test
    public void testLoginUser_Fail() {
        login.registerUser("ab_c", "Pass@123", "+27838968976", "John", "Doe");
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    // Return Login Status Tests
    @Test
    public void testReturnLoginStatus_True() {
        assertEquals("true", login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatus_False() {
        assertEquals("false", login.returnLoginStatus(false));
    }
}