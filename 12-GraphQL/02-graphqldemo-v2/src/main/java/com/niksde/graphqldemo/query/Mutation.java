package com.niksde.graphqldemo.query;

import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.request.CreateStudentRequest;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.service.StudentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    StudentService studentService;

    public StudentResponse addStudent(CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);
    }
}
