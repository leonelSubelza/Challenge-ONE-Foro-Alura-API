package com.ForoAlura.core.model;

import java.time.LocalDateTime;

public class Response {
    private Long id;
    private String mensaje;
    private Topic topico;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private User autor;
    private Boolean solucion = false;
}
