package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
}
