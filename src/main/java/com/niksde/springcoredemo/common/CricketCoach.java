package com.niksde.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Prototype creates new instance on each dependency injection
public class CricketCoach implements Coach {

   public CricketCoach() {
       System.out.println("In constructor: " + getClass().getSimpleName());
   }

   // init method
//    @PostConstruct
//    public void doStartup() {
//        System.out.println("In startup: " + getClass().getSimpleName());
//    }

    // destroy method
//    @PreDestroy
//    public void doCleanup() {
//        System.out.println("In destroy: " + getClass().getSimpleName());
//    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
