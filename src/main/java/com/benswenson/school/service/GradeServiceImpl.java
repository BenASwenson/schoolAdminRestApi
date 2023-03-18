package com.benswenson.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.benswenson.school.entity.Course;
import com.benswenson.school.entity.Grade;
import com.benswenson.school.entity.Student;
import com.benswenson.school.exception.EntityNotFoundException;
import com.benswenson.school.exception.GradeNotFoundException;
import com.benswenson.school.repository.CourseRepository;
import com.benswenson.school.repository.GradeRepository;
import com.benswenson.school.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    GradeRepository gradeRepository;
    CourseRepository courseRepository;
    StudentRepository studentRepository;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> gradeOptional = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return unwrapGrade(gradeOptional, studentId, courseId);
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course = CourseServiceImpl.unwrapCourse(courseRepository.findById(courseId), courseId);
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Optional<Grade> gradeOptional = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade grade = unwrapGrade(gradeOptional, studentId, courseId);
        grade.setScore(score);
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return (List<Grade>) gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return (List<Grade>) gradeRepository.findByCourseId(courseId);
    }

    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(entity.get().getId(), Grade.class);
    }
    
}
