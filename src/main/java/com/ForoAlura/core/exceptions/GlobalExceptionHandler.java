package com.ForoAlura.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//Esta clase intercepta los errores que se enviarán

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //por cada error del tipo ResourceNotFoundException se ejecutará este método y se devolverá un error personalizado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> manejarResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDetails errorDetalles = new ErrorDetails(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles   , HttpStatus.NOT_FOUND);
    }
}
