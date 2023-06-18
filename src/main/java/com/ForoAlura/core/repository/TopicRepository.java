package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    Page<Topic> findAllByOrderByFechaCreacionDesc(Pageable pageable);

    Optional<Topic> findByTitulo(String titulo);
    Optional<Topic> findByMensaje(String mensaje);

}
