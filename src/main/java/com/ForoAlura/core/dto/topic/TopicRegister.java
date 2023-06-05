package com.ForoAlura.core.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegister(
        @NotBlank(message = "El título no puede ser nulo")
        String titulo,
        @NotBlank(message = "El mensaje no puede ser nulo")
        String mensaje,
        @NotNull(message = "El autor no puede ser nulo")
        Long autorId,
        @NotNull(message = "El curso no puede ser nulo")
        Long cursoId
){}


