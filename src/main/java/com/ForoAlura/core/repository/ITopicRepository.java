package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicRepository extends JpaRepository<Topic,Long> {
    Page<Topic> findAllByOrderByFechaCreacionAsc(Pageable pageable);
}
