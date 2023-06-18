package com.ForoAlura.core.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRegisterDTO {
    private Long id;
    private String nombre;
    private String username;
    private String email;
}
