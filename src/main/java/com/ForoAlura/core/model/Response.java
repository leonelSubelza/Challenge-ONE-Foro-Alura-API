package com.ForoAlura.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private Boolean solucion = false;

    //muchas respuestas pertenecen a un solo autor
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Author autor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//muchas respuestas pertenecen a un solo topico
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Topic topico;


}
