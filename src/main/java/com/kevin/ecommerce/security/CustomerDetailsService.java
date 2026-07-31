package com.kevin.ecommerce.security;

import com.kevin.ecommerce.modules.customers.Customer;
import com.kevin.ecommerce.modules.customers.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + email));

        return User.withUsername(customer.getEmail())
                .password(customer.getPasswordHash())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")))
                .disabled(Boolean.FALSE.equals(customer.getIsActive()))
                .build();
    }
}
