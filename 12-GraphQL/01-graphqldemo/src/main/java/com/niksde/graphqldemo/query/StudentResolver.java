package com.niksde.graphqldemo.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.niksde.graphqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Not mapping to graphql schema
@Component
public class StudentResolver implements GraphQLQueryResolver {

    @Autowired
    StudentService studentService;

    public String firstQuery() {
        return "First Query";
    }

    public String secondQuery() {
        return "Second Query";
    }
}
