package com.ForoAlura.core.dto.topic;

import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.Autors;
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
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotNull
    private Autors autor;
    @NotNull
    private Course curso;
}
