package com.ForoAlura.core.repository;

import com.ForoAlura.core.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
