package com.niksde.graphqldemo.controller;

import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.request.CreateStudentRequest;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rest/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public List<StudentResponse> getAllStudents () {
        List<Student> studentList = studentService.getAllStudent();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return  studentResponseList;
    }


    @GetMapping("/firstname/{id}")
    public String getFirstNameById(@PathVariable long id) {
        return studentService.getFirstNameById(id);
    }

    @GetMapping("/lastname/{id}")
    public String getLastNameById(@PathVariable long id) {
        return studentService.getLastNameById(id);
    }

    @PostMapping("/")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);

        return new StudentResponse(student);
    }


}
