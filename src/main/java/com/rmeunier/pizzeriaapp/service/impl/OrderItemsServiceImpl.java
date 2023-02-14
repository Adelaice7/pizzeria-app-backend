package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.model.OrderItems;
import com.rmeunier.pizzeriaapp.repo.OrderItemsRepository;
import com.rmeunier.pizzeriaapp.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Override
    public void saveOrderItems(OrderItems orderItems) {
    }

    @Override
    public void modifyOrderItems(Long id, OrderItems orderItems) {

    }

    @Override
    public void deleteOrderItems(Long id) {

    }

    @Override
    public OrderItems getOrderItems(Long id) {
        return null;
    }
}
