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
    private Boolean active;
}
