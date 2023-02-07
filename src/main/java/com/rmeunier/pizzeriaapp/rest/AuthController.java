package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.error.ApiError;
import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.request.AuthenticationRequest;
import com.rmeunier.pizzeriaapp.service.CustomerService;
import com.rmeunier.pizzeriaapp.shared.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public GenericResponse register(@Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new GenericResponse("Customer saved.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Customer customer = (Customer) userDetailsService.loadUserByUsername(request.getUsername());
        if (customer != null) {
            return ResponseEntity.ok("Customer found, login successful.");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login unsuccessful.");
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

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAccessDeniedException() {
        return new ApiError(HttpStatus.UNAUTHORIZED.value(), "Access error", "/login");
    }
}
