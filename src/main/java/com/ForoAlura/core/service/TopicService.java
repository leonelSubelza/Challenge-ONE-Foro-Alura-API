package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.author.AuthorResponse;
import com.ForoAlura.core.dto.course.CourseResponse;
import com.ForoAlura.core.dto.topic.TopicRegisterDTO;
import com.ForoAlura.core.dto.topic.TopicRegisterResponse;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.exceptions.ResourceNotFoundException;
import com.ForoAlura.core.model.Author;
import com.ForoAlura.core.model.Course;
import com.ForoAlura.core.model.StatusTopico;
import com.ForoAlura.core.model.Topic;
import com.ForoAlura.core.repository.IAuthorRepository;
import com.ForoAlura.core.repository.ICourseRepository;
import com.ForoAlura.core.repository.ITopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public TopicRegisterResponse create(TopicRegisterDTO topic) {
        Course cursoAsociado = this.courseRepository.findById(topic.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso","Id",topic.getCursoId()));
        Author autorAsociado = this.authorRepository.findById(topic.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor","Id",topic.getAutorId()));

        //convertimos de TopicRegisterDTO -> Topic
        Topic topicoCreado = this.topicRepository.save(Topic.builder()
                .titulo(topic.getTitulo())
                .mensaje(topic.getMensaje())
                .fechaCreacion(LocalDateTime.now())
                .status(StatusTopico.NO_RESPONDIDO)
                .autor(autorAsociado)
                .curso(cursoAsociado).build());

        //convertimos de Topic -> TopicRegisterResponse
        return createTopicRegisterResponse(topicoCreado);
    }

    @Override
    public TopicRegisterResponse update(Long id,TopicRegisterDTO topicRegisterDTO) {
        Topic topic = this.topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topico","Id",id));
        Course cursoAsociado = this.courseRepository.findById(topicRegisterDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso","Id",topicRegisterDTO.getCursoId()));
        Author autorAsociado = this.authorRepository.findById(topicRegisterDTO.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor","Id",topicRegisterDTO.getAutorId()));
        topic.setCurso(cursoAsociado);
        topic.setAutor(autorAsociado);
        topic.setTitulo(topicRegisterDTO.getTitulo());
        topic.setMensaje(topicRegisterDTO.getMensaje());
        return createTopicRegisterResponse(this.topicRepository.save(topic));
    }

    @Override
    public TopicRegisterResponse findById(Long id) {
        Topic topic = this.topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topico","Id",id));
        return createTopicRegisterResponse(topic);
    }

    @Override
    public Page<TopicResponseDTO> findAll(Pageable pageable) {
        Page<Topic> topicsPage = topicRepository.findAllByOrderByFechaCreacionDesc(pageable);
//        Page<Topic> topicsPage = iForoRepository.findAll(pageable);
        return topicsPage.map(topicPage -> this.modelMapper.map(topicPage,TopicResponseDTO.class));
    }

    @Override
    public void delete(Long id) {
        this.topicRepository.deleteById(id);
    }


    private TopicRegisterResponse createTopicRegisterResponse(Topic topicoCreado) {
        return new TopicRegisterResponse(
                topicoCreado.getId(),
                topicoCreado.getTitulo(),
                topicoCreado.getMensaje(),
                topicoCreado.getFechaCreacion(),
                topicoCreado.getStatus(),
                new AuthorResponse(topicoCreado.getAutor().getId(),
                        topicoCreado.getAutor().getNombre(),
                        topicoCreado.getAutor().getEmail()),
                new CourseResponse(topicoCreado.getCurso().getId(),
                        topicoCreado.getCurso().getNombre(),
                        topicoCreado.getCurso().getCategoria()));
    }

//    public TopicResponseDTO mapTopicToTopicResponseDTO(Topic topic){
//        return TopicResponseDTO.builder()
//                .titulo(topic.getTitulo())
//                .mensaje(topic.getMensaje())
//                .fechaCreacion(topic.getFechaCreacion())
//                .status(topic.getStatus())
//                .autor(topic.getAutor())
//                .curso(topic.getCurso())
//                .build();
//    }
}
