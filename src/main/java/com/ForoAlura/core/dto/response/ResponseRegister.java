package com.ForoAlura.core.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseRegister (
        @NotBlank(message = "El campo mensaje no puede estar vacío")
        String mensaje,
        @NotNull(message = "El campo idAutor no puede ser nulo")
        Long idAutor,
        @NotNull(message = "El campo idTopico no puede ser nulo")
        Long idTopico
){}
