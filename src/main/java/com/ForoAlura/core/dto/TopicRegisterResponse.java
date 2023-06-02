package com.ForoAlura.core.dto;

import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.StatusTopico;
import com.ForoAlura.core.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record TopicRegisterResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        @Enumerated(EnumType.STRING)
        StatusTopico status,
        User autor,
        Course curso
) {
}
