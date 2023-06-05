package com.ForoAlura.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    //@Embedded//quiere decir que el obj autor se guardará dentro de la tabla topic
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Author autor;

    //un topico tiene muchas respuestas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Response> respuestas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//muchos topicos pertenecen a un solo curso
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Course curso;

    //esto se ejecutará antes de guardar la entidad en la bd
    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }

        if (status == null) {
            status = StatusTopico.NO_RESPONDIDO;
        }
    }


}
