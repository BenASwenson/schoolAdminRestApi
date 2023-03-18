package com.benswenson.school.service;

import java.util.List;

import com.benswenson.school.entity.Course;

public interface CourseService {

    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
    
}
