package com.uber.auth.Authentication.repositories;

import com.uber.auth.Authentication.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findDriverByEmail(String email);
}
