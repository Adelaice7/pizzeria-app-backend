package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.error.ApiError;
import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.service.CustomerService;
import com.rmeunier.pizzeriaapp.shared.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Set<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers")
    public GenericResponse createCustomer(@Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new GenericResponse("User saved.");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());

        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        apiError.setValidationErrors(validationErrors);
        return apiError;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }
}
