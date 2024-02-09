package com.niksde.graphqldemo.response;

import com.niksde.graphqldemo.entity.Subject;

public class SubjectResponse {
    private long id;

    private String subjectName;

    private Double marksObtained;

    public SubjectResponse(Subject subject) {
        id = subject.getId();
        subjectName = subject.getSubjectName();
        marksObtained = subject.getMarksObtained();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }
}
