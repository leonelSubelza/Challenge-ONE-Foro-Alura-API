package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.topic.TopicRegisterDTO;
import com.ForoAlura.core.dto.topic.TopicRegisterResponse;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITopicService {
    public TopicRegisterResponse create(TopicRegisterDTO topic);

    public TopicRegisterResponse update(Long id,TopicRegisterDTO topicRegisterDTO);

    public TopicRegisterResponse findById(Long id);

    public Page<TopicResponseDTO> findAll(Pageable pageable);

    public void delete(Long id);
}
