package com.ForoAlura.core.config;

import com.ForoAlura.core.security.CustomUserDetailsService;
import com.ForoAlura.core.security.JWTAuthenticationEntryPoint;
import com.ForoAlura.core.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*Esta sera una clase de configuracion personalizada que usaremos para encriptar contraseñas*/
@Configuration
@EnableWebSecurity//se habilita la seguridad web
//habilita la seguridad a nivel de método, lo que significa que se pueden aplicar anotaciones
// de seguridad en los métodos para controlar el acceso.
@EnableMethodSecurity//esto es lo mismo que lo de arriba (@EnableMethodSecurity(prePostEnabled = true)) pero actualizado
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //Esta clase se encarga de manejar errores de autenticación de un usuario
    @Autowired
    private JWTAuthenticationEntryPoint authenticationEntryPoint;

    //Se crea el filtro para jwt como un Bean o.o
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Esta clase/Bean manejará los errores de autenticación de un usuario
    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf().disable()
//                Se establece que ante cualquier excepcion que ocurra sera manejada por jwtAuthenticationEntryPoint
                .exceptionHandling()
                .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
//               Establecen la política de creación de sesiones como STATELESS, lo que significa que no se mantendrá un
//               estado de sesión en el servidor, esto es dado que se usaran tokens para la aut., es decir,
//               la aut se hara mediante cada solicitud y no se almacenara info en el serv
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//               las solicitudes HTTP GET a /api/** y /api/auth/** están permitidas sin autenticación.
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/**")
                .permitAll()
                .requestMatchers("/api/auth/**")
                .permitAll()
//               Cualquier otra solicitud debe estar autenticada para acceder a los recursos.
                .anyRequest()
                .authenticated()
                .and()
//               Se añade el filtro para jwt. Cualquier request pasará primero por este filtro sin excepción.
                .addFilterBefore(this.jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //se crean path en white list para swagger
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    //este bean carga en memoria de spring los usuarios que posee la api para autenticar
    /*
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("user").password(passwordEncoder().encode("user")).roles("USER").build();

        UserDetails admin = User.builder()
                .username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }
    */
}