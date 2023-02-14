package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
