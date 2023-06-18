package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.response.ResponseRegister;
import com.ForoAlura.core.dto.response.ResponseReturnDetailedDTO;
import com.ForoAlura.core.service.IResponseService;
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
@RequestMapping("/api/respuestas")
public class ResponseController {

    @Autowired
    private IResponseService responseService;

    @GetMapping
    public ResponseEntity<Page<ResponseReturnDetailedDTO>> readAll(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.responseService.readAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReturnDetailedDTO> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(this.responseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseReturnDetailedDTO> update(@RequestBody @Valid ResponseRegister datosRespuesta,
                                                            @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(this.responseService.update(id,datosRespuesta));
    }

    @PostMapping
    public ResponseEntity<ResponseReturnDetailedDTO> register(@RequestBody @Valid ResponseRegister responseRegister,
                                                              UriComponentsBuilder uriComponentsBuilder){
        ResponseReturnDetailedDTO response =this.responseService.create(responseRegister);
        //esta URL irá asociada en el encabezado de respuesta location
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        this.responseService.delete(id);
        return new ResponseEntity<>("Respuesta eliminada con éxito", HttpStatus.OK);
    }

}
