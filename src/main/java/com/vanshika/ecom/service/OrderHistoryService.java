package com.vanshika.ecom.service;

import com.vanshika.ecom.model.OrderHistory;
import com.vanshika.ecom.model.User;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderHistoryService{

    Iterable<OrderHistory> findByUser(User user);
}
