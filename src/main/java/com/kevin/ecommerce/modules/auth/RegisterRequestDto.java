package com.kevin.ecommerce.modules.auth;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String email;
    private String password;
    private String fullName;
    private String phone;
}
