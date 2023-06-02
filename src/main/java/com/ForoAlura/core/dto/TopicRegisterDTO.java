package com.ForoAlura.core.dto;

import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.User;
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
    private User autor;
    @NotNull
    private Course curso;
}
