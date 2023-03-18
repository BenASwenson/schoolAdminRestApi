package com.benswenson.school.repository;

import org.springframework.data.repository.CrudRepository;

import com.benswenson.school.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    
}
