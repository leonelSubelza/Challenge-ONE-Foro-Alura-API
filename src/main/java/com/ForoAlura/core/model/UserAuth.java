package com.ForoAlura.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "usuarios",
        uniqueConstraints = {
//        Establecemos que las columnas username y email deben ser únicas
                @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
//          La tabla que se creara para especificar la union muchos a muchos entre Usuario y Roles se llamara usuario_roles
            name = "usuario_roles",
// Para la primera entidad que se usara dentro de la tabla usuario_roles será el 'id' de esta clase con el nombre usuario_id
            joinColumns =
            @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
//La otra tabla que se usará será la de la clase Rol para el atr "id", en la tabla se llamará rol_id
            inverseJoinColumns =
            @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
