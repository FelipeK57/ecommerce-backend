package com.kevin.ecommerce.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /** Base64-encoded HMAC key. Must decode to at least 32 bytes for HS256. */
    private String secret;

    /** Token lifetime in milliseconds. */
    private long expiration;
}
