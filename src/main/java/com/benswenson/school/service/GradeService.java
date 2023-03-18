package com.benswenson.school.service;

import java.util.List;

import com.benswenson.school.entity.Grade;

public interface GradeService {
    Grade getGrade(Long student_id, Long course_id);
    Grade saveGrade(Grade grade, Long student_id, Long course_id);
    void deleteGrade(Long student_id, Long course_id);
    Grade updateGrade(String score, Long student_id, Long course_id);
    List<Grade> getGrades();
    List<Grade> getStudentGrades(Long student_id);
    List<Grade> getCourseGrades(Long course_id);
}
