package com.ForoAlura.core.dto.topic;

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
    //esto ya no va :/
//    @Enumerated(EnumType.STRING)
//    private StatusTopico status;
//    private AutorResponseDTO autor;
//    private CourseResponseDTO curso;
}
