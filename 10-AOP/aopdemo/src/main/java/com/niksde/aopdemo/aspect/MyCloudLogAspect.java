package com.niksde.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(-40)
public class MyCloudLogAspect {
    @Before("com.niksde.aopdemo.aspect.AppAopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAdvice() {
        System.out.println("=====>>> logToCloudAdvice");
    }
}
