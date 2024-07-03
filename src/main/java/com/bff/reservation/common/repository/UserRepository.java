package com.bff.reservation.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bff.reservation.common.model.Role;
import com.bff.reservation.common.model.Users;

import java.util.Optional;

@Repository
@EnableJpaRepositories(basePackages = "com.bff.reservation.common.repository")
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByEmail(String email);

    Users findByRole(Role role);
}
