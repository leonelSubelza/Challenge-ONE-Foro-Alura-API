package com.ForoAlura.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ApiForoAluraException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public ApiForoAluraException(HttpStatus estado, String msj, String msj1){
        this.estado=estado;
        this.mensaje=msj;
        this.mensaje=msj1;
    }
}