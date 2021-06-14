/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

    private @Id
    @GeneratedValue
    Long id; // <2>
    private String firstName;
    private String lastName;
    private String description;
    private String email;

    private Employee() {
    }

    public Employee(String firstName, String lastName, String description, String email) {
        verifyFirstName(firstName);
        verifyLastName(lastName);
        verifyDescription(description);
        verifyEmail(email);
    }

    private void verifyEmail(String email) {
        if (validateString(email) && checkFormat(email)) {
            this.email = email;
        } else {
            this.email = "Email is not valid";
        }
    }

    // Extracted from https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private boolean checkFormat(String email) {
        String emailRegex = "[A-Z0-9a-z._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private void verifyFirstName(String name) {
        if (validateString(name)) {
            this.firstName = name;
        } else {
            this.firstName = "Name is not valid";
        }
    }

    private void verifyLastName(String name) {
        if (validateString(name)) {
            this.lastName = name;
        } else {
            this.lastName = "Name is not valid";
        }
    }

    private void verifyDescription(String description) {
        if (validateString(description)) {
            this.description = description;
        } else {
            this.description = "Description is not valid";
        }
    }

    private boolean validateString(String string) {
        boolean valid;
        valid = string != null && !string.isEmpty() && string.trim().length() != 0;
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(description, employee.description) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, description, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
// end::code[]
