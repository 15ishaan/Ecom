package com.vanshika.ecom.controller;

import com.vanshika.ecom.model.User;
import com.vanshika.ecom.repository.RegistrationRepository;
import com.vanshika.ecom.service.EmailService;
import com.vanshika.ecom.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class ForgotPasswordController {

    @Autowired
    private RegistrationService service;

    @Autowired
    private RegistrationRepository registrationRepo;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgotPassword/{username}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> forgotPassword(@PathVariable String username){
        User user = service.fetchUserByUsername(username);
        String password = getDecodedString(user.getPassword());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Forgot Password!");
        mailMessage.setFrom("gomailsender@gmail.com");
        mailMessage.setText("It's okay if you have forgotten your password, your Password is: " + password + "\n\nYou can further change your password from: ClickNShip >> Profile Section >> Change Password\n\n\n\nRegards: @Team ClickNShip");

        emailService.sendEmail(mailMessage);
        ResponseEntity.status(200);
        return ResponseEntity.ok("Your password has been sent to your registered email.");
    }

    public static String getDecodedString(String password){
        return new String(Base64.getMimeDecoder().decode(password));
    }
}
