package com.ForoAlura.core.dto.topic;

import com.ForoAlura.core.dto.author.AuthorResponse;
import com.ForoAlura.core.dto.course.CourseResponse;
import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.StatusTopico;
import com.ForoAlura.core.model.Author;
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
        AuthorResponse autor,
        CourseResponse curso
) {
}
