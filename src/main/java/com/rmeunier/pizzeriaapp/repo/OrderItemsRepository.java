package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
