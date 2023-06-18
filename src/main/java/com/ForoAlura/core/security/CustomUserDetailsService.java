package com.ForoAlura.core.security;


import com.ForoAlura.core.model.Role;
import com.ForoAlura.core.model.UserAuth;
import com.ForoAlura.core.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
Esta clase se utiliza para cargar los detalles de un usuario desde la base de datos
y crear un objeto UserDetails personalizado que se utiliza durante el proceso de autenticación
Se fija si existe el usuario y contraseña que se envió desde el cliente
También se puede hacer que el model de User implemente UserDetails para manejar más detalles
* */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAuthRepository usuarioRepository;

    //    Carga un usuario por nombre de usuario
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserAuth usuario = this.usuarioRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado con el username o email: "+ usernameOrEmail));
        return new User(usuario.getEmail(),usuario.getPassword(),mapearRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearRoles(Set<Role> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }
}