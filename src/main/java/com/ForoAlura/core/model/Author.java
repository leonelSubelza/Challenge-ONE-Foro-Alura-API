package com.ForoAlura.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

    //un usuario tiene muchas respuestas hechas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Response> respuestas;

    //un usuario tiene muchas publicaciones hechas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Topic> topicos;
}
