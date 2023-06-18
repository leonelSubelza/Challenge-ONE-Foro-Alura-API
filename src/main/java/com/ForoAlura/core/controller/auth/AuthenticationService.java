package com.ForoAlura.core.controller.auth;

import com.ForoAlura.core.dto.auth.AuthenticationRegisterDTO;
import com.ForoAlura.core.dto.auth.AuthenticationRequest;
import com.ForoAlura.core.dto.auth.AuthenticationResponseDTO;
import com.ForoAlura.core.dto.auth.RegisterRequest;
import com.ForoAlura.core.exceptions.ConstraintViolationException;
import com.ForoAlura.core.exceptions.ResourceNotFoundException;
import com.ForoAlura.core.model.Role;
import com.ForoAlura.core.model.UserAuth;
import com.ForoAlura.core.repository.RoleRepository;
import com.ForoAlura.core.repository.UserAuthRepository;
import com.ForoAlura.core.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    @Autowired
    private UserAuthRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthenticationRegisterDTO register(RegisterRequest request){
        //si ya existe lanzamos error
        this.userRepository.findByUsername(request.username())
                .ifPresent(user -> {
                    throw new ConstraintViolationException("Usuarios", "username", request.username());
                });

        this.userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new ConstraintViolationException("Usuarios", "email", request.email());
                });

        Role roles = this.roleRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Rol","nombre ROLE_ADMIN",null));

        UserAuth userSaved = this.userRepository.save(UserAuth.builder()
                .nombre(request.nombre())
                .username(request.username())
                .email(request.email())
                .password(this.passwordEncoder.encode(request.password()))
                .roles(Collections.singleton(roles))
                .build());
//        return ResponseEntity.ok(modelMapper.map(userSaved, AuthenticationResponseDTO.class));
        return modelMapper.map(userSaved, AuthenticationRegisterDTO.class);
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequest request) {
        //representa la autenticación del usuario
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.usernameOrEmail(),request.password()));
        //se establece la autenticación en el contexto de seguridad.
        //Al establecer la autenticación en el contexto de seguridad, se indica que el usuario ha sido autenticado correctamente.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.tokenProvider.generarTokenDeAcceso(authentication);
        return AuthenticationResponseDTO.builder().token(token).tipoToken("Bearer").build();
    }
}
