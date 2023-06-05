package com.ForoAlura.core.dto.response;

import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReturnDetailedDTO {
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean solucion;
    private AuthorResponseDTO autor;
    private TopicResponseDTO topico;
}
