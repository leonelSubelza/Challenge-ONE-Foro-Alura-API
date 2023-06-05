package com.ForoAlura.core.dto.author;

import com.ForoAlura.core.dto.response.ResponseReturnDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDetailedResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private List<ResponseReturnDTO> respuestas;
    private List<TopicResponseDTO> publicaciones;
}
