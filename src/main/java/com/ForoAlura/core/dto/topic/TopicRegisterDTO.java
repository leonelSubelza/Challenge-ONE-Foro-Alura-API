package com.ForoAlura.core.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicRegisterDTO {
    @NotBlank(message = "El título no puede ser nulo")
    private String titulo;
    @NotBlank(message = "El mensaje no puede ser nulo")
    private String mensaje;
    @NotNull(message = "El autor no puede ser nulo")
    private Long autorId;
    @NotNull(message = "El curso no puede ser nulo")
    private Long cursoId;
}
