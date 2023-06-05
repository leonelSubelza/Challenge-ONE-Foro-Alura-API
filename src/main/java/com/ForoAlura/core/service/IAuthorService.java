package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.author.AuthorDetailedResponseDTO;
import com.ForoAlura.core.dto.author.AuthorRegister;
import com.ForoAlura.core.dto.author.AuthorResponseDTO;
import com.ForoAlura.core.dto.topic.TopicDetailedDTO;
import com.ForoAlura.core.dto.topic.TopicRegister;
import com.ForoAlura.core.dto.topic.TopicRegisterResponseDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IAuthorService {
    public Page<AuthorResponseDTO> findAll(Pageable pageable);
    public AuthorDetailedResponseDTO findById(Long id);
    public AuthorResponseDTO update(Long id, AuthorRegister topicRegisterDTO);
    public AuthorResponseDTO create(AuthorRegister topic);
    public void delete(Long id);
}
