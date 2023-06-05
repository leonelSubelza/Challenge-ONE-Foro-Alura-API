package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.dto.response.ResponseRegister;
import com.ForoAlura.core.dto.response.ResponseReturnDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.model.Response;
import com.ForoAlura.core.repository.IResponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import  org.springframework.data.domain.Pageable;

@Service
public class ResponseService implements IResponseService{

    @Autowired
    private IResponseRepository responseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ResponseReturnDTO> readAll(Pageable pageable) {
        Page<Response> responsePages = this.responseRepository.findAll(pageable);
        //Convertimos una entidad Response a un ResponseReturnDTO con modelMapper (también mapea sus objetos internos
        //authorResponseDTO y courseResponseDTO al parecer gracias a que las variables tienen el mismo nombre o.O
        return responsePages.map(responsePage -> this.modelMapper.map(responsePage,ResponseReturnDTO.class));
    }

    @Override
    public ResponseReturnDTO findById(Long id) {
        return null;
    }

    @Override
    public ResponseReturnDTO update(Long id, ResponseRegister responseRegister) {
        return null;
    }

    @Override
    public ResponseReturnDTO create(ResponseRegister responseRegister) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
