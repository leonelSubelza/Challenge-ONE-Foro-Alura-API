package com.ForoAlura.core.dto.topic;

import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.StatusTopico;
import com.ForoAlura.core.model.Autors;
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
        Autors autor,
        Course curso
) {
}
