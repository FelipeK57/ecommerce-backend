package com.kevin.ecommerce.modules.auth;

import com.kevin.ecommerce.modules.customers.Customer;
import com.kevin.ecommerce.modules.customers.CustomerRepository;
import com.kevin.ecommerce.modules.customers.CustomerResponseDto;
import com.kevin.ecommerce.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponseDto register(RegisterRequestDto data) {
        Customer customer = Customer.builder()
                .email(data.getEmail())
                .passwordHash(passwordEncoder.encode(data.getPassword()))
                .fullName(data.getFullName())
                .phone(data.getPhone())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
        try {
            customerRepository.save(customer);
            return CustomerResponseDto.from(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register customer: " + e.getMessage());
        }
    }

    public LoginResponseDto login(LoginRequestDto data) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword())
        );

        Customer customer = customerRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        String token = jwtService.generateToken(customer.getEmail(), Map.of("id", customer.getId()));

        return LoginResponseDto.builder()
                .accessToken(token)
                .customer(CustomerResponseDto.from(customer))
                .build();
    }
}
