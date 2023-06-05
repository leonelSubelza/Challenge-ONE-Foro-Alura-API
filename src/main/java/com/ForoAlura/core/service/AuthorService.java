package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.author.AuthorDetailedResponseDTO;
import com.ForoAlura.core.dto.author.AuthorRegister;
import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.dto.response.ResponseReturnDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.exceptions.ResourceNotFoundException;
import com.ForoAlura.core.model.Author;
import com.ForoAlura.core.repository.IAuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements IAuthorService{
    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AuthorResponseDTO> findAll(Pageable pageable) {
        Page<Author> autores =  authorRepository.findAll(pageable);
        return autores.map(authorPage -> modelMapper.map(authorPage,AuthorResponseDTO.class));
    }

    @Override
    public AuthorDetailedResponseDTO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor","id",id));
        return AuthorDetailedResponseDTO.builder()
                .id(author.getId())
                .nombre(author.getNombre())
                .email(author.getEmail())
                .respuestas(author.getRespuestas().stream().map(
                        r -> modelMapper.map(r, ResponseReturnDTO.class)).collect(Collectors.toList()))
                .publicaciones(author.getTopicos().stream().map(
                        t -> modelMapper.map(t, TopicResponseDTO.class)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public AuthorResponseDTO update(Long id, AuthorRegister topicRegisterDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor","id",id));
        author.setNombre(topicRegisterDTO.nombre());
        author.setEmail(topicRegisterDTO.email());
        //aca abria que desencriptar la contraseña
        author.setContrasena(topicRegisterDTO.contrasena());
        return modelMapper.map(authorRepository.save(author),AuthorResponseDTO.class);

    }

    @Override
    public AuthorResponseDTO create(AuthorRegister topic) {
        System.out.println("objeto recibido: "+topic);
        Author author = authorRepository.save(Author.builder()
                .nombre(topic.nombre())
                .email(topic.email())
                .contrasena(topic.contrasena())
                .build());
        return modelMapper.map(author,AuthorResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor","id",id));
        authorRepository.deleteById(id);
    }
}
