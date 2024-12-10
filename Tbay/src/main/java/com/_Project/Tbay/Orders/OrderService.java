package com._Project.Tbay.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(long sellerId, long listingId, long userId){
        Date date = new Date(System.currentTimeMillis());
        Order order = new Order(0, sellerId, userId, listingId, date ,false);
        orderRepository.save(order);
    }


}
