package com.vanshika.ecom.model;

public class EditUserRequest {

    private String Username;
    private String firstName;
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
