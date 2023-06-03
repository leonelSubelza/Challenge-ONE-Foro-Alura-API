package com.ForoAlura.core.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Cunado se llama a esta clase se retorna el atr NOT_FOUND
@ResponseStatus(value= HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String nombreDelRecurso;
    private String nombreDelCampo;
    private Long valorDelCampo;

    //esto se enviará en el cuerpo de la respuesta
    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, Long valorDelCampo) {
        super(String.format("%s no encontrado con : %s : '%s'",nombreDelRecurso,nombreDelCampo,valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }
}