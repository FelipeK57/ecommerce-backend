package com.kevin.ecommerce.modules.staff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffUserRepository extends JpaRepository<StaffUser, Long> {
    StaffUser findByEmail(String email);
}
