package com.ForoAlura.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;
}
