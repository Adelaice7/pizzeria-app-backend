package com.rmeunier.pizzeriaapp.model;

import com.rmeunier.pizzeriaapp.shared.UniqueUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 2, max = 255)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 255)
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull(message = "{com.rmeunier.pizzeriaapp.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    private String username;
    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "{com.rmeunier.pizzeriaapp.password.Pattern.message}")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_details_id", referencedColumnName = "id")
    private OrderDetails orderDetails;

    @Enumerated(EnumType.STRING)
    private Role role;

    private long createdAt = new Date().getTime();

    private long modifiedAt = new Date().getTime();

    private long deletedAt = -1L;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String username, String password, OrderDetails orderDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.orderDetails = orderDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
