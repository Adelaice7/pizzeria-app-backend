package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.model.OrderDetails;
import com.rmeunier.pizzeriaapp.model.OrderItems;
import com.rmeunier.pizzeriaapp.model.Product;
import com.rmeunier.pizzeriaapp.service.CustomerService;
import com.rmeunier.pizzeriaapp.service.OrderDetailsService;
import com.rmeunier.pizzeriaapp.service.OrderItemsService;
import com.rmeunier.pizzeriaapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/cart")
public class CartController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestParam("productId") Long productId,
                                                   @RequestParam("quantity") Integer quantity,
                                                   @RequestParam("customerId") Long customerId) {

        //TODO fix this
        Product product = productService.getProduct(productId);
        Customer customer = customerService.getCustomer(customerId);

        OrderItems orderItems = new OrderItems();
        orderItems.setProduct(product);
        orderItems.setQuantity(quantity);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setTotal(orderDetails.getTotal() + orderItems.getProduct().getPrice());
        orderDetails.setCustomer(customer);

        orderDetails.getOrderItems().add(orderItems);
        orderDetailsService.saveOrderDetails(orderDetails);
        return ResponseEntity.ok("Successfully added product to cart.");
    }
}
