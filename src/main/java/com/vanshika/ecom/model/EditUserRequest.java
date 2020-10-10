package com.vanshika.ecom.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditUserRequest {

    private String Username;

    @Pattern(
            regexp = "^[a-zA-Z_-]{3,30}$",
            message = "Enter valid first name"
    )
    private String firstName;

    @Size(min = 3, max = 30)
    private String lastName;
    private String gender;

    public EditUserRequest(String username, String firstName, String lastName, String gender) {
        Username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public EditUserRequest() {

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
