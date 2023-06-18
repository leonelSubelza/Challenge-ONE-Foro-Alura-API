package com.ForoAlura.core.security;

import com.ForoAlura.core.exceptions.ApiForoAluraException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //obtenemos el valor del application.properties
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationInMs;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public String generarTokenDeAcceso(Authentication authentication){
        System.out.println("se genera token de acceso");
        String username = authentication.getName();
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaExpiracion = fechaActual.plus(Long.parseLong(this.jwtExpirationInMs), ChronoUnit.MILLIS);

//      se retorna el token
        return Jwts.builder()
//              se Configura el sujeto/nombre del creador del token (subject)
                .setSubject(username)
//              se establece la fecha de emisión poniendo la fecha actual
                .setIssuedAt(new Date())
//              se establece la fecha de expiracion - se convierte LocalDate a Date
                .setExpiration(Date.from(fechaExpiracion.atZone(ZoneId.systemDefault()).toInstant()))
//              se firma el token utilizando el algoritmo de firma HS512 y la clave secreta (es más seguro usar HS256)
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
//               Compacta el token en una cadena de texto utilizando
                .compact();
    }


//    public String obtenerUsernameDelJWT(String token){
//      Construimos un parserJWT
//        Claims claims = Jwts.parserBuilder()
//              Esto establece la clave de firma utilizada para verificar el token JWT.
//                .setSigningKey(getSigninKey())
//              encadenamos los métodos build, parseClaimsJws y getBody para analizar el token y obtener el cuerpo del JWT
//              en forma de Claims, que contiene la información asociada al token.
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }


    public boolean esTokenValido(String token,Claims claims, UserDetails userDetails) {
        try {
            //obtenemos el username del token
            String username=claims.getSubject();
            return !isTokenExpired(claims) && username.equals(userDetails.getUsername());
        } catch (JwtException e) {
            throw new ApiForoAluraException(HttpStatus.BAD_REQUEST, "El token JWT es inválido o ha expirado", e.getMessage());
        }
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private Key getSigninKey() {
        //convertimos la key a bytes
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
//        dado los bytes convertimos esos bytes en un Key objeto porque asi lo pide el método setSigningKey
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
