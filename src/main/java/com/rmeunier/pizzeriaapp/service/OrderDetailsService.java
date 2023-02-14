package com.rmeunier.pizzeriaapp.service;

import com.rmeunier.pizzeriaapp.model.OrderDetails;

public interface OrderDetailsService {

    void saveOrderDetails(OrderDetails orderDetails);

    void updateOrderDetails(Long id, OrderDetails orderDetails);

    void deleteOrderDetails(Long id);

    OrderDetails getOrderDetails(Long id);
}
