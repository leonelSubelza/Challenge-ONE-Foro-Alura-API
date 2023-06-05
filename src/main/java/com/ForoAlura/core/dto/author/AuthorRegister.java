package com.ForoAlura.core.dto.author;

import jakarta.validation.constraints.NotBlank;

public record AuthorRegister(
        @NotBlank(message = "El título no puede ser nulo")
        String nombre,
        @NotBlank(message = "El título no puede ser nulo")
        String email,
        @NotBlank(message = "El título no puede ser nulo")
        String contrasena
) {
}
