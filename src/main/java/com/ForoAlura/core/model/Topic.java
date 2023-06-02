package com.ForoAlura.core.model;

import com.ForoAlura.core.dto.TopicRegisterDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data//tiene implementado los getter y setter y otros métodos
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topicos", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo","mensaje"})})
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status=StatusTopico.NO_RESPONDIDO;

    @Embedded//quiere decir que el obj autor se guardará dentro de la tabla topic
    private User autor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//muchos topicos pertenecen a un solo curso
    private Course curso;

    //un topico tiene muchas respuestas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Response> respuestas;

}
