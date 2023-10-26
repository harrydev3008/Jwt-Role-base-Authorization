package com.harry.JwtRoleBaseAuthorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harry.JwtRoleBaseAuthorization.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);

    Optional<Role> findByName(String name);
}