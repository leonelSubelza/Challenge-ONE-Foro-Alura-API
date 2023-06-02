package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.TopicRegisterDTO;
import com.ForoAlura.core.dto.TopicRegisterResponse;
import com.ForoAlura.core.dto.TopicResponseDTO;
import com.ForoAlura.core.model.Response;
import com.ForoAlura.core.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IForoService {
    public TopicRegisterResponse create(TopicRegisterDTO topic);

    public Topic update(Topic topic);

    public Topic findById(Long id);

    public Page<TopicResponseDTO> findAll(Pageable pageable);

    public void delete(Long id);
}
