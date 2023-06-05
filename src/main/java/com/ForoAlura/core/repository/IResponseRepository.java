package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResponseRepository extends JpaRepository<Response,Long> {
}
