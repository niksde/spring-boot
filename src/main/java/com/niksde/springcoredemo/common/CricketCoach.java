package com.niksde.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Prototype creates new instance on each dependency injection
public class CricketCoach implements Coach {

   public CricketCoach() {
       System.out.println("In constructor:" + getClass().getSimpleName());
   }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
