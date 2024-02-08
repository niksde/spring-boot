package com.niksde.graphqldemo.service;

import com.niksde.graphqldemo.entity.Address;
import com.niksde.graphqldemo.entity.Student;
import com.niksde.graphqldemo.entity.Subject;
import com.niksde.graphqldemo.repository.AddressRepository;
import com.niksde.graphqldemo.repository.StudentRepository;
import com.niksde.graphqldemo.repository.SubjectRepository;
import com.niksde.graphqldemo.request.CreateStudentRequest;
import com.niksde.graphqldemo.request.CreateSubjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getAllStudent() {
        return  studentRepository.findAll();
    }

    public String getFirstNameById(long id) {
        return studentRepository.findById(id).get().getFirstName();
    }

    public String getLastNameById(long id) {
        return studentRepository.findById(id).get().getLastName();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);
        Address address = new Address();

        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        student.setAddress(address);

        student = studentRepository.save(student);

        List<Subject> subjectList = new ArrayList<Subject>();

        if(createStudentRequest.getSubjectsLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest: createStudentRequest.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);

                subjectList.add(subject);
            }
            subjectRepository.saveAll(subjectList);
        }

        student.setLearningSubjects(subjectList);

        return student;
    }

    public Student getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) {
            throw new RuntimeException("Invalid Id");
        }
        return student.get();
    }
}
