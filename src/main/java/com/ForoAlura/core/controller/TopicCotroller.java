package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.topic.TopicRegisterDTO;
import com.ForoAlura.core.dto.topic.TopicRegisterResponse;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.service.ITopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicCotroller {

    @Autowired
    private ITopicService topicService;

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> readAll(@PageableDefault( size = 2) Pageable paginacion){
//        if(paginacion==null){
//            paginacion = PageRequest.of(0, 2);
//        }
        return ResponseEntity.ok(this.topicService.findAll(paginacion));
    }

    @PostMapping
    public ResponseEntity<TopicRegisterResponse> register(@RequestBody @Valid TopicRegisterDTO datosRegistro,
                                                          UriComponentsBuilder uriComponentsBuilder){
        TopicRegisterResponse response =this.topicService.create(datosRegistro);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }
}
