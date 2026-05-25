//LoginTest
package com.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testCheckUserName_Success() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Failure() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexity_Success() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Failure() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber_Success() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_Failure() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUser_Success() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.", result);
    }

    @Test
    public void testLoginUser_Success() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatus_True() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        
        String expected = "Welcome Kyle ,Smith it is great to see you.";
        String actual = login.returnLoginStatus(true);
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatus_False() {
        Login login = new Login();
        String expected = "Username or password incorrect, please try again.";
        String actual = login.returnLoginStatus(false);
        assertEquals(expected, actual);
    }
}