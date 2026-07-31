package com.kevin.ecommerce.modules.auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
