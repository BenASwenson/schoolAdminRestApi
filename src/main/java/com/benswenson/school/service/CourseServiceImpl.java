package com.benswenson.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.benswenson.school.entity.Course;
import com.benswenson.school.exception.CourseNotFoundException;
import com.benswenson.school.exception.EntityNotFoundException;
import com.benswenson.school.repository.CourseRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return unwrapCourse(courseOptional, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Course.class);
    }
    
}
