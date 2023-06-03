package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.topic.TopicRegisterDTO;
import com.ForoAlura.core.dto.topic.TopicRegisterResponse;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.service.IForoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@CrossOrigin("*")
@RestController
@RequestMapping("/topicos")
public class ForoCotroller {

    @Autowired
    private IForoService iForoService;

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> readAll(@PageableDefault( size = 2) Pageable paginacion){
//        if(paginacion==null){
//            paginacion = PageRequest.of(0, 2);
//        }
        return ResponseEntity.ok(this.iForoService.findAll(paginacion));
    }

    @PostMapping
    public ResponseEntity<TopicRegisterResponse> register(@RequestBody @Valid TopicRegisterDTO datosRegistro,
                                                          UriComponentsBuilder uriComponentsBuilder){
        TopicRegisterResponse response =this.iForoService.create(datosRegistro);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }
}
