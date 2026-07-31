package com.kevin.ecommerce.modules.auth;

import com.kevin.ecommerce.modules.customers.CustomerResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";
    private long expiresIn;
    private CustomerResponseDto customer;
}
