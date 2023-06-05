package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.response.ResponseRegister;
import com.ForoAlura.core.dto.response.ResponseReturnDetailedDTO;
import com.ForoAlura.core.exceptions.ResourceNotFoundException;
import com.ForoAlura.core.model.Author;
import com.ForoAlura.core.model.Response;
import com.ForoAlura.core.model.Topic;
import com.ForoAlura.core.repository.IAuthorRepository;
import com.ForoAlura.core.repository.IResponseRepository;
import com.ForoAlura.core.repository.ITopicRepository;
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
    private ITopicRepository topicRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ResponseReturnDetailedDTO> readAll(Pageable pageable) {
        Page<Response> responsePages = this.responseRepository.findAll(pageable);
        //Convertimos una entidad Response a un ResponseReturnDTO con modelMapper (también mapea sus objetos internos
        //authorResponseDTO y courseResponseDTO al parecer gracias a que las variables tienen el mismo nombre o.O
        return responsePages.map(responsePage -> this.modelMapper.map(responsePage, ResponseReturnDetailedDTO.class));
    }

    @Override
    public ResponseReturnDetailedDTO findById(Long id) {
        Response response = this.responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta","id",id));
        return this.modelMapper.map(response, ResponseReturnDetailedDTO.class);
    }

    @Override
    public ResponseReturnDetailedDTO update(Long id, ResponseRegister responseRegister) {
        Response response = this.responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta","id",id));
        Topic topicoAsociado = this.topicRepository.findById(responseRegister.idTopico())
                .orElseThrow(() -> new ResourceNotFoundException("Topico","Id",responseRegister.idTopico()));
        Author autorAsociado = this.authorRepository.findById(responseRegister.idAutor())
                .orElseThrow(() -> new ResourceNotFoundException("Autor","Id",responseRegister.idAutor()));
        response.setMensaje(responseRegister.mensaje());
        response.setAutor(autorAsociado);
        response.setTopico(topicoAsociado);
        return this.modelMapper.map(response, ResponseReturnDetailedDTO.class);
    }

    @Override
    public ResponseReturnDetailedDTO create(ResponseRegister responseRegister) {
        Topic topicoAsociado = this.topicRepository.findById(responseRegister.idTopico())
                .orElseThrow(() -> new ResourceNotFoundException("Topico","Id",responseRegister.idTopico()));
        Author autorAsociado = this.authorRepository.findById(responseRegister.idAutor())
                .orElseThrow(() -> new ResourceNotFoundException("Autor","Id",responseRegister.idAutor()));
        Response response = this.responseRepository.save(Response.builder()
                .mensaje(responseRegister.mensaje())
                .autor(autorAsociado)
                .topico(topicoAsociado)
                .build());
        return this.modelMapper.map(response, ResponseReturnDetailedDTO.class);
    }

    @Override
    public void delete(Long id) {
        Response response = this.responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta","id",id));
        this.responseRepository.delete(response);
    }
}
