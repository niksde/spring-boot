package com.niksde.graphqldemo.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.request.CreateStudentRequest;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

//@Component
//public class Query implements GraphQLQueryResolver {
//    public String firstQuery() {
//        return "First Query";
//    }
//}

@Controller
public class Query {

    @Autowired
    StudentService studentService;

    @QueryMapping
    public List<StudentResponse> students() {
        List<Student> studentList = studentService.getAllStudent();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return  studentResponseList;
    }

    @QueryMapping
    public StudentResponse studentById(@Argument Long id) {
        Student student = studentService.getById(id);
        return new StudentResponse(student);
    }

    @MutationMapping(name = "addStudent")
    public StudentResponse createStudent(@Arguments CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);
    }

}
