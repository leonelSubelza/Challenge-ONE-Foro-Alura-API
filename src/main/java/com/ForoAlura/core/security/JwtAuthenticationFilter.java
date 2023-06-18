package com.ForoAlura.core.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/*
Esta clase será un filtro que se ejecutara primero antes que cualquier cosa. Validará si el cliente que hace la request
esta autenticado o no
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //  este método extrae el token JWT de la solicitud, lo valida, obtiene el nombre de usuario del token, carga los
//  detalles del usuario. Esto ocurre por cada request que haga el usuario antes de que entre al controller
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("se ejecuta el filtro de cada request");

        //Se carga el token
        String token = obtenerJWTDeLaSolicitud(request);
        if(SecurityContextHolder.getContext().getAuthentication() != null || !StringUtils.hasText(token) ){
            System.out.println("la request ya estaba autenticada o no habia token, se sigue con el siguiente filtro");
            filterChain.doFilter(request,response);
            return;
        }

        //Si existe el token, se cargan los detalles del token
        Claims claims = this.jwtTokenProvider.getClaims(token);
        String username = claims.getSubject();
        //Se cargan los detalles del usuario obtenido de la bd
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
        //si la request tiene un token y es válido y aún no hizo una autenticación
        if(this.jwtTokenProvider.esTokenValido(token,claims,userDetails)){

//          Se crea un objeto UsernamePasswordAuthenticationToken utilizando los detalles del usuario cargados y
//          las autoridades del usuario (userDetails.getAuthorities()). Este objeto representa la autenticación del usuario.
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//          se establecen los detalles adicionales de autenticación utilizando WebAuthenticationDetailsSource para
//          obtener los detalles de la solicitud HTTP y se los asigna al objeto authenticationToken.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//          se establece la autenticación del usuario en el contexto de seguridad utilizando SecurityContextHolder. Esto
//          permite que el usuario se considere autenticado y se le conceda acceso a los recursos protegidos.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        //si el usuario no trajo un token puede ser que va a autenticarse o consumir un endpoint get

//      se llama al método doFilter del objeto filterChain para continuar con el procesamiento de la solicitud por
//      parte de otros filtros en la cadena de filtros
        filterChain.doFilter(request,response);
    }

    //    Bearer token de acceso. Obtiene la clave generada
    private String obtenerJWTDeLaSolicitud(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization");
//      Se obtiene el Bearer que viene dentro de la solicitud http, dentro hay un campo del
//      tipo Authorization: Bearer afdsdfsa... de donde recortamos la cadena "Bearer " y nos quedamos con el token de la sol
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
