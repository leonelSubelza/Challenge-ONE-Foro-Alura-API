package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth,Long> {
    public Optional<UserAuth> findByEmail(String email);
    public Optional<UserAuth> findByUsernameOrEmail(String username, String email);
    public Optional<UserAuth> findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}