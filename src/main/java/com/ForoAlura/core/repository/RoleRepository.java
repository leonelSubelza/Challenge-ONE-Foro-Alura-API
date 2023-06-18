package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByNombre(String nombre);
}