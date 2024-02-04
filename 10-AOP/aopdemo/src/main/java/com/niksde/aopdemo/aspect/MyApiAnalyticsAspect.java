package com.niksde.aopdemo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(30)
public class MyApiAnalyticsAspect {
    @Before("com.niksde.aopdemo.aspect.AppAopExpressions.forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("=====>>> Performing API analytics");
    }

    @After("com.niksde.aopdemo.aspect.AppAopExpressions.forDaoPackageNoGetterSetter()")
    public void newLine() {
        System.out.println("\n");
    }
}
