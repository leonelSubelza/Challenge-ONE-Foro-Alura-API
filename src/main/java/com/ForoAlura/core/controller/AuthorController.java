package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.author.AuthorDetailedResponseDTO;
import com.ForoAlura.core.dto.author.AuthorRegister;
import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.service.IAuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/autores")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<AuthorResponseDTO>> readAll(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.authorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDetailedResponseDTO> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(this.authorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> update(@RequestBody @Valid AuthorRegister datosRespuesta,
                                                            @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(this.authorService.update(id,datosRespuesta));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> register(@RequestBody @Valid AuthorRegister responseRegister,
                                                              UriComponentsBuilder uriComponentsBuilder){
        AuthorResponseDTO response =this.authorService.create(responseRegister);
        //esta URL irá asociada en el encabezado de respuesta location
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        this.authorService.delete(id);
        return new ResponseEntity<>("Autor eliminado con éxito", HttpStatus.OK);
    }
}
