package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.topic.TopicRegisterDTO;
import com.ForoAlura.core.dto.topic.TopicRegisterResponse;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.service.ITopicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /*La diferencia entre pasar variables por la URL mediante {id} es que de esta forma se utiliza para identificar
    * recursos específicos y se considera a este valor como parte de la URL (para hacer un findById
    * Y usar un &id=2 se usa generalmente para paginar los resultados y no se consideran a estos valores como parte de
    * la URL*/

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> readAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<TopicRegisterResponse> register(@RequestBody @Valid TopicRegisterDTO datosRegistro,
                                                          UriComponentsBuilder uriComponentsBuilder){
        TopicRegisterResponse response =this.topicService.create(datosRegistro);
        //esta URL irá asociada en el encabezado de respuesta location
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicRegisterResponse> detailTopic(@PathVariable(value = "id") @NotNull Long id){
        return ResponseEntity.ok(this.topicService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicRegisterResponse> update(@RequestBody @Valid TopicRegisterDTO datosRegistro,
                                                        @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(this.topicService.update(id,datosRegistro));
    }
}
