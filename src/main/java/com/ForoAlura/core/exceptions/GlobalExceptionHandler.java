package com.ForoAlura.core.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Esta clase intercepta los errores que se enviarán

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //por cada error del tipo ResourceNotFoundException se ejecutará este método y se devolverá un error personalizado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> manejarResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDetails errorDetalles = new ErrorDetails(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles   , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
//        String errorMessage = "Error de constraint duplicado";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /*Cuando se intenta consumir un endpoint sin los permisos necesarios se retorna error 403 Forbidden indicando
    que la solicitud fue procesada pero se niega a enviar una respuesta. Al pibe del video le devolvía error 500 "Access is denied"
    pero a mi me devolvía error 403 "Forbidden" asique le puse que devuelva en vez de 'Forbidden' que devuelva "Access is denied"
    con codigo 403 en vez de 500
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Access is denied", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    //Error general de la api o.O
    @ExceptionHandler(ApiForoAluraException.class)
    public ResponseEntity<ErrorDetails> manejarBlogAppException(ApiForoAluraException ex, WebRequest webRequest){
        ErrorDetails errorDetalles = new ErrorDetails(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    //    Esta excepcion se encarga de manejar las excepciones en general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> manejarGlobalException(Exception ex, WebRequest webRequest){
        ErrorDetails errorDetalles = new ErrorDetails(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Este método maneja las excepciones de validación
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( e -> {
            String nombreCampo = ((FieldError)e).getField();
            String msj = e.getDefaultMessage();
            errores.put(nombreCampo,msj);
        });//obtiene todos los errores y por cada uno se lo agrega al map que se retorna al cliente
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
