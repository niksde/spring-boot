package com.niksde.graphqldemo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.entity.Subject;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.response.SubjectResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {
    public List<SubjectResponse> getLearningSubjects (StudentResponse studentResponse) {
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
