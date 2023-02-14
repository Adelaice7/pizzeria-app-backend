package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.model.OrderDetails;
import com.rmeunier.pizzeriaapp.repo.OrderDetailsRepository;
import com.rmeunier.pizzeriaapp.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void updateOrderDetails(Long id, OrderDetails orderDetails) {
        orderDetailsRepository.findById(id).ifPresent(oldOrderDetails -> {
            oldOrderDetails.setOrderItems(orderDetails.getOrderItems());
            oldOrderDetails.setTotal(orderDetails.getTotal());
            orderDetailsRepository.save(oldOrderDetails);
        });
    }

    @Override
    public void deleteOrderDetails(Long id) {
        orderDetailsRepository.findById(id).ifPresent(orderDetails -> {
            orderDetailsRepository.delete(orderDetails);
        });
    }

    @Override
    public OrderDetails getOrderDetails(Long id) {
        return orderDetailsRepository.findById(id).get();
    }
}
