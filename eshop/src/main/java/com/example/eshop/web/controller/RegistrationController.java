package com.example.eshop.web.controller;

import com.example.eshop.model.AuthenticationRequest;
import com.example.eshop.model.AuthenticationResponse;
import com.example.eshop.model.ConfirmationToken;
import com.example.eshop.model.User;
import com.example.eshop.repository.ConfirmationTokenRepository;
import com.example.eshop.repository.RegistrationRepository;
import com.example.eshop.service.EmailService;
import com.example.eshop.service.RegistrationService;
import com.example.eshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;


@RestController
@Validated
public class RegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegistrationService service;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private RegistrationRepository userRepository;

    //registeration and sending of verification mail
    @PostMapping("/registeruser")
    //@CrossOrigin
    public User registerUser(@RequestBody User user) throws Exception {
        //checking if email already exists
        String tempUsername = user.getUsername();
        String tempPassword = user.getPassword();
        String tempConfirmPassword = user.getConfirmPassword();

        if(tempUsername != null && !"".equals(tempUsername)){
            User userObj = service.fetchUserByUsername(tempUsername);

           if(userObj != null){
               throw new Exception("user with " + tempUsername + "already exists");
            }
        }

        //checking if password matches with confirm_Password
        if(tempPassword.equals(tempConfirmPassword)){
            User userObj = null;
            userObj = service.saveUser(user);

            //accessing token
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);

            //sending verification email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getUsername());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("gomailsender@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            return userObj;
        }
        else{
            throw new Exception("Password must match");
        }
    }

    //verification of emailId
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByUsername(token.getUser().getUsername());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

    //login for user already registered
    @PostMapping("/login")
    //@CrossOrigin
    public User loginUser(@RequestBody User user) throws Exception {
        String tempUsername = user.getUsername();
        String tempPass = getEncodedString(user.getPassword());

        User userObj = null;
        if(tempUsername != null && tempPass != null){
               userObj = service.fetchUserByUsernameAndPassword(tempUsername, tempPass);
        }
        if(userObj == null) throw new Exception("Bad Credentials");
        else{
           userObj.setEnabled(true);
            return userObj;
        }

    }

    //authenticating token of jwt authentication
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        String pass = getEncodedString(authenticationRequest.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), pass)
            );
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("incorrect password or email.");
        }
        final UserDetails userDetails = service
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt =jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    //encoding incoming password to check the encoded password in database
    private String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

}
