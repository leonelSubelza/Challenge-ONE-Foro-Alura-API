package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.dto.course.CourseResponseDTO;
import com.ForoAlura.core.dto.response.ResponseReturnDTO;
import com.ForoAlura.core.dto.response.ResponseReturnDetailedDTO;
import com.ForoAlura.core.dto.topic.TopicDetailedDTO;
import com.ForoAlura.core.dto.topic.TopicRegister;
import com.ForoAlura.core.dto.topic.TopicRegisterResponseDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.exceptions.ResourceNotFoundException;
import com.ForoAlura.core.model.Author;
import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.Topic;
import com.ForoAlura.core.repository.IAuthorRepository;
import com.ForoAlura.core.repository.ICourseRepository;
import com.ForoAlura.core.repository.ITopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<TopicResponseDTO> findAll(Pageable pageable) {
        Page<Topic> topicsPage = topicRepository.findAllByOrderByFechaCreacionDesc(pageable);
//        Page<Topic> topicsPage = iForoRepository.findAll(pageable);
        return topicsPage.map(topicPage -> this.modelMapper.map(topicPage,TopicResponseDTO.class));
    }

    @Override
    public TopicDetailedDTO findById(Long id) {
        Topic topic = this.topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topico","Id",id));
        return TopicDetailedDTO.builder()
                .id(topic.getId())
                .titulo(topic.getTitulo())
                .mensaje(topic.getMensaje())
                .fechaCreacion(topic.getFechaCreacion())
                .status(topic.getStatus()).autor(this.modelMapper.map(topic.getAutor(), AuthorResponseDTO.class))
                .curso(this.modelMapper.map(topic.getCurso(), CourseResponseDTO.class))
                .respuestas(topic.getRespuestas().stream().map(
                        r -> this.modelMapper.map(r, ResponseReturnDTO.class)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public TopicRegisterResponseDTO update(Long id, TopicRegister topicRegister) {
        Topic topic = this.topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topico","Id",id));
        Course cursoAsociado = this.courseRepository.findById(topicRegister.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso","Id",topicRegister.cursoId()));
        Author autorAsociado = this.authorRepository.findById(topicRegister.autorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor","Id",topicRegister.autorId()));
        topic.setCurso(cursoAsociado);
        topic.setAutor(autorAsociado);
        topic.setTitulo(topicRegister.titulo());
        topic.setMensaje(topicRegister.mensaje());
        return this.modelMapper.map(topic,TopicRegisterResponseDTO.class);
//        return createTopicRegisterResponse(this.topicRepository.save(topic));
    }

    @Override
    public TopicRegisterResponseDTO create(TopicRegister topic) {
        Course cursoAsociado = this.courseRepository.findById(topic.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso","Id",topic.cursoId()));
        Author autorAsociado = this.authorRepository.findById(topic.autorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor","Id",topic.autorId()));

        //convertimos de TopicRegister -> Topic (no usamos modelMapper porque hay que setear los obj curso y autor
        Topic topicoCreado = this.topicRepository.save(Topic.builder()
                .titulo(topic.titulo())
                .mensaje(topic.mensaje())
                .autor(autorAsociado)
                .curso(cursoAsociado).build());

        //convertimos de Topic -> TopicRegisterResponseDTO
        return this.modelMapper.map(topicoCreado,TopicRegisterResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        Topic topic = this.topicRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Tópico","id",id));
        this.topicRepository.deleteById(id);
    }

}
