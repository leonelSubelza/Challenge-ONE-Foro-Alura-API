package com.ForoAlura.core.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "El username o email no puede ser vacío")
        String usernameOrEmail,
        @NotBlank(message = "La contraseña no puede ser vacía")
        String password
) {}
