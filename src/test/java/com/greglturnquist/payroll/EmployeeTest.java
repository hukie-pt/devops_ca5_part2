package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeTest {

    String invalidName = "Name is not valid";
    String invalidDescription = "Description is not valid";
    String invalidEmail = "Email is not valid";

    @Test
    void createValidEmployee() {
        String firstName = "Samwise";
        String lastName = "Gamgee";
        String description = "true hero";
        String email = "samtheman@email.net";

        Employee valid = new Employee(firstName, lastName, description, email);

        assertEquals(valid.getFirstName(), firstName);
        assertEquals(valid.getLastName(), lastName);
        assertEquals(valid.getDescription(), description);
        assertEquals(valid.getEmail(), email);
    }

    @Test
    void createInvalidEmployeeNull() {
        String firstName = null;
        String lastName = null;
        String description = null;
        String email = null;

        Employee valid = new Employee(firstName, lastName, description, email);

        assertEquals(valid.getFirstName(), invalidName);
        assertEquals(valid.getLastName(), invalidName);
        assertEquals(valid.getDescription(), invalidDescription);
        assertEquals(valid.getEmail(), invalidEmail);
    }

    @Test
    void createInvalidEmployeeEmpty() {
        String firstName = "";
        String lastName = "";
        String description = "";
        String email = "";

        Employee valid = new Employee(firstName, lastName, description, email);

        assertEquals(valid.getFirstName(), invalidName);
        assertEquals(valid.getLastName(), invalidName);
        assertEquals(valid.getDescription(), invalidDescription);
        assertEquals(valid.getEmail(), invalidEmail);
    }

    @Test
    void createInvalidEmployeeBlank() {
        String firstName = "                ";
        String lastName = "  ";
        String description = "       ";
        String email = " ";

        Employee valid = new Employee(firstName, lastName, description, email);

        assertEquals(valid.getFirstName(), invalidName);
        assertEquals(valid.getLastName(), invalidName);
        assertEquals(valid.getDescription(), invalidDescription);
        assertEquals(valid.getEmail(), invalidEmail);
    }

}