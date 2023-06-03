package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author,Long> {
}
