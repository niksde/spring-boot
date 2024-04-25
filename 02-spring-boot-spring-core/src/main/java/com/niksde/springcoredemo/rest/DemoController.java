package com.niksde.springcoredemo.rest;

import com.niksde.springcoredemo.common.Coach;
import com.niksde.springcoredemo.service.PythonFileRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    @Autowired
    PythonFileRunner pythonFileRunner;

// constructor injection - required dependencies
    @Autowired
    public DemoController(@Qualifier("aquatic") Coach theCoach) {
        System.out.println("In constructor: "+ getClass().getSimpleName());
        myCoach = theCoach;
    }

    // setter injection - optional dependencies
//    @Autowired
//    public void setCoach(Coach theCoach) {
//        myCoach = theCoach;
//    }
    
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/python")
    public boolean runPython() {
        return pythonFileRunner.runFile();
    }
}
