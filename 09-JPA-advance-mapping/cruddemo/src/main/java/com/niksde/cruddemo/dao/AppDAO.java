package com.niksde.cruddemo.dao;

import com.niksde.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
}
