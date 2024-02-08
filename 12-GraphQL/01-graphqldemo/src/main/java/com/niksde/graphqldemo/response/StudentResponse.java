package com.niksde.graphqldemo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.entity.Subject;
import com.niksde.graphqldemo.request.CreateSubjectRequest;

import java.util.ArrayList;
import java.util.List;

public class StudentResponse {

    private long id;

    @JsonProperty("first_name")
    private String firstName;

    private String lastName;

    private String email;

    private String street;

    private String city;

    private List<SubjectResponse> learningSubjects;

    public StudentResponse(Student student) {
        id = student.getId();
        firstName = student.getFirstName();
        lastName = student.getLastName();
        email = student.getEmail();

        street = student.getAddress().getStreet();
        city = student.getAddress().getCity();

        if(student.getLearningSubjects() != null) {
            learningSubjects = new ArrayList<SubjectResponse>();
            for (Subject subject : student.getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<SubjectResponse> getLearningSubjects() {
        return learningSubjects;
    }

    public void setLearningSubjects(List<SubjectResponse> learningSubjects) {
        this.learningSubjects = learningSubjects;
    }
}
