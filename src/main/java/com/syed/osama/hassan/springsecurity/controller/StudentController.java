package com.syed.osama.hassan.springsecurity.controller;

import com.syed.osama.hassan.springsecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "John Doe"),
            new Student(2, "Mark Turner"),
            new Student(3, "Peter Tam")
    );

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {

        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " does not exist"));
    }
}
