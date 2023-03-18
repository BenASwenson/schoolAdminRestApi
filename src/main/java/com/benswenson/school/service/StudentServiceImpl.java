package com.benswenson.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.benswenson.school.entity.Student;
import com.benswenson.school.exception.EntityNotFoundException;
import com.benswenson.school.exception.StudentNotEnrolledException;
import com.benswenson.school.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return unwrapStudent(optionalStudent, id);       
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Student.class);
    }
    
}
