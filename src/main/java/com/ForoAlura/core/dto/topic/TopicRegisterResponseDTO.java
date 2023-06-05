package com.ForoAlura.core.dto.topic;

import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.dto.course.CourseResponseDTO;
import com.ForoAlura.core.model.StatusTopico;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicRegisterResponseDTO {
        private Long id;
        private String titulo;
        private String mensaje;
        private LocalDateTime fechaCreacion;
        @Enumerated(EnumType.STRING)
        private StatusTopico status;
        private AuthorResponseDTO autor;
        private CourseResponseDTO curso;
}
