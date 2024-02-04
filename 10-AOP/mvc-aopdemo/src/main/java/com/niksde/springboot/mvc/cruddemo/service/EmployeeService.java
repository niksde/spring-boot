package com.niksde.springboot.mvc.cruddemo.service;

import com.niksde.springboot.mvc.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
