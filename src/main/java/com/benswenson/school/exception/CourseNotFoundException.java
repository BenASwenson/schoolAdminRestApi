package com.benswenson.school.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long id) {
        super("The course with id: '" + id + "' does not exist in our records");
    }
    
}
