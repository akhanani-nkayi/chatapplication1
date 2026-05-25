/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

/**
 *
 * @author Student
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    @Test
    public void testLoginSuccess() {
        login user = new login();
        //It registers correct details
        user.RegisterUser("Ak_7","Fr0sty#8","+27412555690");
        assertTrue(user.LoginUser("Ak_7", "Fr0sty#8"));
    }
    
    //Test login fail
    @Test
    public void testLoginFail() {
        login user = new login();
        user.RegisterUser("Ak_7", "Fr0sty#8", "+27412555690");
        
        //When user enters wrong details
        assertFalse(user.LoginUser("Akha!!!!!!", "password"));
    }

}
