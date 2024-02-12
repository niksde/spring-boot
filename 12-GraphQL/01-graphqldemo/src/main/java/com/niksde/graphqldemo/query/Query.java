package com.niksde.graphqldemo.query;

import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.entity.Subject;
import com.niksde.graphqldemo.request.CreateStudentRequest;
import com.niksde.graphqldemo.request.SampleRequest;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.response.SubjectResponse;
import com.niksde.graphqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Query {

    @Autowired
    StudentService studentService;

    @QueryMapping(name = "students")
    public List<StudentResponse> getStudentList() {
        List<Student> studentList = studentService.getAllStudent();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return  studentResponseList;
    }

    @QueryMapping(name = "student")
    public StudentResponse getStudent(@Argument long id) {
        return new StudentResponse(studentService.getStudentById(id));
    }

    @MutationMapping(name = "addStudent")
    public StudentResponse createStudent(@Arguments CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);
    }

    @QueryMapping
    public String fullName(@Argument SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }

    @SchemaMapping
    public List<SubjectResponse> learningSubjects (StudentResponse studentResponse) {
        Student student = studentResponse.getStudent();

        List<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>();

        if(student.getLearningSubjects() != null) {
            for (Subject subject : student.getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }

        return learningSubjects;
    }

}
