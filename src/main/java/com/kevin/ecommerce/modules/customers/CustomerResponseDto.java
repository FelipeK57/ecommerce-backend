package com.kevin.ecommerce.modules.customers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private Boolean isActive;

    public static CustomerResponseDto from(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .fullName(customer.getFullName())
                .phone(customer.getPhone())
                .isActive(customer.getIsActive())
                .build();
    }
}
