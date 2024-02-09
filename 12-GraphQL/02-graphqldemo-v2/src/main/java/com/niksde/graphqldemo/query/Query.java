package com.niksde.graphqldemo.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.request.SampleRequest;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    StudentService studentService;

    public String fullName(SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }

    public List<StudentResponse> students() {
        List<Student> studentList = studentService.getAllStudent();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    public StudentResponse getStudent(long id) {
        return new StudentResponse(studentService.getStudentById(id));
    }
}
