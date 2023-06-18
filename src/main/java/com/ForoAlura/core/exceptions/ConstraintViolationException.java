package com.ForoAlura.core.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
@Getter
@Setter
public class ConstraintViolationException extends RuntimeException{

    private String nombreDelRecurso;
    private String nombreDelCampo;
    private String valorDelCampo;

    //esto se enviará en el cuerpo de la respuesta
    public ConstraintViolationException(String nombreDelRecurso, String nombreDelCampo, String valorDelCampo) {
        super(String.format("Error de constraint en la entidad %s. El campo %s ya existe con el nombre '%s'"
                ,nombreDelRecurso,nombreDelCampo,valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }
}
