package com.vanshika.ecom.controller;

import com.vanshika.ecom.model.EditPasswordRequest;
import com.vanshika.ecom.model.EditUserRequest;
import com.vanshika.ecom.model.User;
import com.vanshika.ecom.repository.RegistrationRepository;
import com.vanshika.ecom.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class UserEditorController {

    @Autowired
    private RegistrationService service;

    @Autowired
    private RegistrationRepository registrationRepo;

    @PostMapping("/editUser")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public User editUser(@RequestBody EditUserRequest editUserRequest){
        User user = service.fetchUserByUsername(editUserRequest.getUsername());
        user.setFirstName(editUserRequest.getFirstName());
        user.setLastName(editUserRequest.getLastName());
        user.setGender(editUserRequest.getGender());
        registrationRepo.save(user);
        return user;
    }

    @PostMapping("/editPassword")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public User editPassword(@RequestBody EditPasswordRequest editPasswordRequest) throws Exception{

        String pass = getEncodedString(editPasswordRequest.getPassword());
        String newPassword = editPasswordRequest.getNewPassword();
        String newConfirmPassword = editPasswordRequest.getNewConfirmPassword();
        User user = service.fetchUserByUsernameAndPassword(editPasswordRequest.getUsername(),pass);

        if(user == null) throw new Exception("Wrong password");

        if(newPassword.equals(newConfirmPassword)) {
            user.setPassword(getEncodedString(newPassword));
            user.setConfirmPassword(getEncodedString(newConfirmPassword));
            registrationRepo.save(user);
            return user;
        }
        else{
            throw new Exception("password and confirm password must match");
        }
    }

    private String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

}
