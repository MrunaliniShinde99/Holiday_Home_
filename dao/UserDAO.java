package com.holiday.home.dao;

import com.holiday.home.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<Customer, Long> {
    Optional<Customer> findUserByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndActive(String email, Boolean active);
    Boolean existsByRole(String role);

}
