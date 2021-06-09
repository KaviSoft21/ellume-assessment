package com.kavishaparanamana.quoteofthedayapp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kavishaparanamana.quoteofthedayapp.controllers.EmailSenderController;

public class EmailSenderControllerTest {

    @Test
    public void testValidateInvalidEmail() {
        boolean actual = EmailSenderController.isValidEmailAddress("asss");
        boolean expected = false;
        assertEquals("Invalid email address.", expected, actual);

    }

    @Test
    public void testValidateValidEmail() {
        boolean actual = EmailSenderController.isValidEmailAddress("asderfff@fmail.com");
        boolean expected = true;
        assertEquals("Email address is valid.", expected, actual);

    }

}



