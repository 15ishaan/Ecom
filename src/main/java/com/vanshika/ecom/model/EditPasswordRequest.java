package com.vanshika.ecom.model;

import javax.validation.constraints.Size;

public class EditPasswordRequest {
    private String username;
    private String password;

    @Size(min = 6, max = 30)
    private String newPassword;
    private String newConfirmPassword;

    public EditPasswordRequest(String username, String password, String newPassword, String newConfirmPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.newConfirmPassword = newConfirmPassword;
    }

    public EditPasswordRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewConfirmPassword() {
        return newConfirmPassword;
    }

    public void setNewConfirmPassword(String newConfirmPassword) {
        this.newConfirmPassword = newConfirmPassword;
    }
}
