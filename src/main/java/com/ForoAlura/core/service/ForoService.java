package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.topic.TopicRegisterDTO;
import com.ForoAlura.core.dto.topic.TopicRegisterResponse;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.model.Topic;
import com.ForoAlura.core.repository.IForoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForoService implements IForoService {

    @Autowired
    private IForoRepository iForoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TopicRegisterResponse create(TopicRegisterDTO topic) {
//        Topic topicoCreado = this.iForoRepository.save(Topic.builder()
//                .titulo(topic.getTitulo())
//                .mensaje(topic.getMensaje())
//                .autor(topic.getAutor())
//                .curso(topic.getCurso())
//                .build());
        Topic topicoCreado = this.iForoRepository.save( this.modelMapper.map(topic,Topic.class));
        return this.modelMapper.map(topicoCreado,TopicRegisterResponse.class);
    }

    @Override
    public Topic update(Topic topic) {
        return this.iForoRepository.save(topic);
    }

    @Override
    public Topic findById(Long id) {
        Optional<Topic> topicOptional = this.iForoRepository.findById(id);
        return topicOptional.orElse(null);
    }

    @Override
    public Page<TopicResponseDTO> findAll(Pageable pageable) {
        Page<Topic> topicsPage = iForoRepository.findAllByOrderByFechaCreacionAsc(pageable);
//        Page<Topic> topicsPage = iForoRepository.findAll(pageable);
        return topicsPage.map(topicPage -> this.modelMapper.map(topicPage,TopicResponseDTO.class));
    }

    @Override
    public void delete(Long id) {
        this.iForoRepository.deleteById(id);
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
