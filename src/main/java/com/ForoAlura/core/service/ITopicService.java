package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.topic.TopicDetailedDTO;
import com.ForoAlura.core.dto.topic.TopicRegister;
import com.ForoAlura.core.dto.topic.TopicRegisterResponseDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITopicService {
    public Page<TopicResponseDTO> findAll(Pageable pageable);
    public TopicDetailedDTO findById(Long id);
    public TopicRegisterResponseDTO update(Long id, TopicRegister topicRegisterDTO);
    public TopicRegisterResponseDTO create(TopicRegister topic);
    public void delete(Long id);
}
