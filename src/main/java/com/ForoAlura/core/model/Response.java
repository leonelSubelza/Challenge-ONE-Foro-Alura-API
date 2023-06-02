package com.ForoAlura.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "respuestas")
@Entity
public class Response {
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//muchas respuestas pertenecen a un solo topico
    private Topic topico;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Embedded
    private User autor;
    private Boolean solucion = false;
}
