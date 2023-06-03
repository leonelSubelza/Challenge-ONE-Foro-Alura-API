package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course,Long> {
}
