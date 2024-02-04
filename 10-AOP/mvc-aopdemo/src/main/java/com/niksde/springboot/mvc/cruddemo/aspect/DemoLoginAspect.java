package com.niksde.springboot.mvc.cruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoginAspect {
    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.niksde.springboot.mvc.cruddemo.controller.*.*(..))")
    private void forControllerPackage() { }

    @Pointcut("execution(* com.niksde.springboot.mvc.cruddemo.service.*.*(..))")
    private void forServicePackage() { }

    @Pointcut("execution(* com.niksde.springboot.mvc.cruddemo.dao.*.*(..))")
    private void forDaoPackage() { }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFLow() {}

    @Before("forAppFLow()")
    public void before(JoinPoint theJoinpoint) {
        String theMethod = theJoinpoint.getSignature().toShortString();
        myLogger.info("====>> in @Before: calling method: "+ theMethod);

        Object[] args = theJoinpoint.getArgs();

        for (Object tempArg: args) {
            myLogger.info("====>> argument: "+ tempArg);
        }
    }

    @AfterReturning(pointcut = "forAppFLow()", returning = "theResult")
    public void afterReturning(JoinPoint theJoinpoint, Object theResult) {
        String theMethod = theJoinpoint.getSignature().toShortString();
        myLogger.info("====>> in @AfterReturning: from method: "+ theMethod);

        myLogger.info("====>> result: "+ theResult);

    }
}
