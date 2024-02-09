package com.niksde.graphqldemo.entity;

import com.niksde.graphqldemo.request.CreateStudentRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Subject> learningSubjects;

    public Student (CreateStudentRequest createStudentRequest) {
        firstName = createStudentRequest.getFirstName();
        lastName = createStudentRequest.getLastName();
        email = createStudentRequest.getEmail();
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Subject> getLearningSubjects() {
        return learningSubjects;
    }

    public void setLearningSubjects(List<Subject> learningSubjects) {
        this.learningSubjects = learningSubjects;
    }
}
