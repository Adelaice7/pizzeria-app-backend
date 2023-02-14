package com.rmeunier.pizzeriaapp.service;

import com.rmeunier.pizzeriaapp.model.OrderItems;

public interface OrderItemsService {
    void saveOrderItems(OrderItems orderItems);

    void modifyOrderItems(Long id, OrderItems orderItems);

    void deleteOrderItems(Long id);

    OrderItems getOrderItems(Long id);
}
