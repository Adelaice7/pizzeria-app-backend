package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.error.ApiError;
import com.rmeunier.pizzeriaapp.model.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> handleLogin(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        if (user != null) {
            return ResponseEntity.ok("AUTHENTICATED");
        }
        return ResponseEntity.status(400).body("An error occurred");
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAccessDeniedException() {
        return new ApiError(HttpStatus.UNAUTHORIZED.value(), "Access error", "/login");
    }
}
