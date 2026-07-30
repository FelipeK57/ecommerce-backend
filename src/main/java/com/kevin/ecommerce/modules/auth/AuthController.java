package com.kevin.ecommerce.modules.auth;

import com.kevin.ecommerce.modules.customers.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        CustomerResponseDto customer = authService.register(registerRequestDto);
        return ResponseEntity.ok(customer);
    }
}
