package com.benswenson.school.repository;

import org.springframework.data.repository.CrudRepository;

import com.benswenson.school.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

    
    
}
