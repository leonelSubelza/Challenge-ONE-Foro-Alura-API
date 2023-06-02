package com.ForoAlura.core.dto;

import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.StatusTopico;
import com.ForoAlura.core.model.User;
import jakarta.persistence.Embedded;
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
public class TopicResponseDTO {
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    private User autor;
    private Course curso;
}
