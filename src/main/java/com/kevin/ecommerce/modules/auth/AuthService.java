package com.kevin.ecommerce.modules.auth;

import com.kevin.ecommerce.modules.customers.Customer;
import com.kevin.ecommerce.modules.customers.CustomerRepository;
import com.kevin.ecommerce.modules.customers.CustomerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;

    public CustomerResponseDto register(RegisterRequestDto data) {
        Customer customer = Customer.builder()
                .email(data.getEmail())
                .passwordHash(data.getPassword()) // In a real application, hash the password before storing it
                .fullName(data.getFullName())
                .phone(data.getPhone())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
        try {
            customerRepository.save(customer);
            return CustomerResponseDto.builder()
                    .id(customer.getId())
                    .email(customer.getEmail())
                    .fullName(customer.getFullName())
                    .phone(customer.getPhone())
                    .active(customer.getIsActive())
                    .build();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to register customer: " + e.getMessage());
        }
    }
}
