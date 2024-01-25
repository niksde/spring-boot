package com.niksde.springcoredemo.config;

import com.niksde.springcoredemo.common.Coach;
import com.niksde.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aquatic")
    public Coach swimCoach() {
        System.out.println("custom coach injection");
        return new SwimCoach();
    }

}
