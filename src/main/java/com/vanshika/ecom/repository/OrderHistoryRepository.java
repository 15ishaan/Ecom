package com.vanshika.ecom.repository;

import com.vanshika.ecom.model.OrderHistory;
import com.vanshika.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    Iterable<OrderHistory> findByUser(User user);
}
