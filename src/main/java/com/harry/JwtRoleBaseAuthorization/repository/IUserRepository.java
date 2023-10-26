package com.harry.JwtRoleBaseAuthorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harry.JwtRoleBaseAuthorization.model.ApplicationUser;

@Repository
public interface IUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);

    boolean existsByUsername(String username);
}