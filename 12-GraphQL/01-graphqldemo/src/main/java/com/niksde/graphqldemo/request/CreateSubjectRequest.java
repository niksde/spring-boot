package com.niksde.graphqldemo.request;

public class CreateSubjectRequest {
    private String subjectName;

    private Double marksObtained;

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

    @Override
    public String toString() {
        return "CreateSubjectRequest{" +
                "subjectName='" + subjectName + '\'' +
                ", marksObtained=" + marksObtained +
                '}';
    }
}
