package com.ForoAlura.core.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

//Este objeto sirve para registrar un nuevo usuario para autenticar
public record RegisterRequest(
        @NotBlank(message = "El nombre no puede ser vacío")
        String nombre,
        @NotBlank(message = "El username no puede ser vacío")
        String username,
        @NotBlank(message = "El email no puede ser vacío")
        String email,
        @NotBlank(message = "La contraseña no puede ser vacía")
        String password

) {
}
