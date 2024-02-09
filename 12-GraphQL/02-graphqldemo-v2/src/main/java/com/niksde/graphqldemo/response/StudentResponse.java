package com.niksde.graphqldemo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niksde.graphqldemo.entity.Student;
import lombok.Getter;
import lombok.Setter;

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

    private Student student;

    private String fullName;

    public StudentResponse(Student student) {
        this.student = student;
        id = student.getId();
        firstName = student.getFirstName();
        lastName = student.getLastName();
        email = student.getEmail();

        street = student.getAddress().getStreet();
        city = student.getAddress().getCity();
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
