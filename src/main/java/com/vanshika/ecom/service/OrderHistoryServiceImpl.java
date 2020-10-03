package com.vanshika.ecom.service;

import com.vanshika.ecom.model.OrderHistory;
import com.vanshika.ecom.model.User;
import com.vanshika.ecom.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public Iterable<OrderHistory> findByUser(User user) {
        return orderHistoryRepository.findByUser(user);
    }
}
