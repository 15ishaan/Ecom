package com.vanshika.ecom.controller;

import com.vanshika.ecom.model.OrderHistory;
import com.vanshika.ecom.model.User;
import com.vanshika.ecom.repository.OrderHistoryRepository;
import com.vanshika.ecom.service.OrderHistoryService;
import com.vanshika.ecom.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderHistoryController {

    @Autowired
    private OrderHistoryRepository orderHistoryRepo;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private RegistrationService service;

    @GetMapping("/orderHistory/{username}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Iterable<OrderHistory>> orderHistory(@PathVariable String username){
        User user = service.fetchUserByUsername(username);
        Iterable<OrderHistory> list = orderHistoryRepo.findByUser(user);
        return new ResponseEntity<Iterable<OrderHistory>>(list, HttpStatus.OK);

    }
}
