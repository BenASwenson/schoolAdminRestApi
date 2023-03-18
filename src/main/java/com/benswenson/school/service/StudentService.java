package com.benswenson.school.service;

import java.util.List;

import com.benswenson.school.entity.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
}
