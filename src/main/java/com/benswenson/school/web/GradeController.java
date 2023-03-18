package com.benswenson.school.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benswenson.school.entity.Grade;
import com.benswenson.school.service.GradeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    @GetMapping("/student/{student_id}/course/{course_id}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long student_id, @PathVariable Long course_id) {
        return new ResponseEntity<Grade>(gradeService.getGrade(student_id, course_id), HttpStatus.OK);
    }

    @PostMapping("/student/{student_id}/course/{course_id}")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade, @PathVariable Long student_id, @PathVariable Long course_id) {
        return new ResponseEntity<Grade>(gradeService.saveGrade(grade, student_id, course_id), HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{student_id}/course/{course_id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long student_id, @PathVariable Long course_id) {
        gradeService.deleteGrade(student_id, course_id);
        return new ResponseEntity<Grade>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/student/{student_id}/course/{course_id}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long student_id, @PathVariable Long course_id) {
        return new ResponseEntity<Grade>(gradeService.updateGrade(grade.getScore(), student_id, course_id), HttpStatus.OK);
    }

    @GetMapping("/student/{student_id}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long student_id) {
        return new ResponseEntity<>(gradeService.getStudentGrades(student_id), HttpStatus.OK);
    }

    @GetMapping("/course/{course_id}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long course_id) {
        return new ResponseEntity<>(gradeService.getCourseGrades(course_id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(gradeService.getGrades(), HttpStatus.OK);
    }
    
}
