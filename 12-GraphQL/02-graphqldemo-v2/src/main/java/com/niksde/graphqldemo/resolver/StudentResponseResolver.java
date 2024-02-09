package com.niksde.graphqldemo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.entity.Subject;
import com.niksde.graphqldemo.enums.SubjectNameFilter;
import com.niksde.graphqldemo.response.StudentResponse;
import com.niksde.graphqldemo.response.SubjectResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {
    public List<SubjectResponse> getLearningSubjects (StudentResponse studentResponse, List<SubjectNameFilter> subjectNameFilters) {

        List<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>();

        if(studentResponse.getStudent().getLearningSubjects() != null) {
            for (Subject subject : studentResponse.getStudent().getLearningSubjects()) {

                if(subjectNameFilters.stream().filter(subjectNameFilter ->
                        subjectNameFilter.name().equalsIgnoreCase("ALL")
                        || subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName())
                ).count() > 0) {
                    learningSubjects.add(new SubjectResponse(subject));
                }
            }
        }

        return learningSubjects;
    }

    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }

}
