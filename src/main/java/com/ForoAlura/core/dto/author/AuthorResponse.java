package com.ForoAlura.core.dto.author;

public record AuthorResponse(
        Long id,
        String nombre,
        String email
) {
}
