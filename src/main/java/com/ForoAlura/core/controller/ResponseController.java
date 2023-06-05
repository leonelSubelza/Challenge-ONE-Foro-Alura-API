package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.response.ResponseReturnDTO;
import com.ForoAlura.core.service.IResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuestas")
public class ResponseController {

    @Autowired
    private IResponseService responseService;

    @GetMapping
    public ResponseEntity<Page<ResponseReturnDTO>> readAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.responseService.readAll(pageable));
    }

}
