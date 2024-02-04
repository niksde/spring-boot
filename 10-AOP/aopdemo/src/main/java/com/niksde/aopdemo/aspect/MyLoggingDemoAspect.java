package com.niksde.aopdemo.aspect;

import com.niksde.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyLoggingDemoAspect {

    //    runs in success or failure
    @After("execution(* com.niksde.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @After (finally) on method: "+ method);
    }
    @Before("com.niksde.aopdemo.aspect.AppAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeLoggingDemo(JoinPoint theJoinPoint) {
        System.out.println("=====>>> Executing @Before advice on method");
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

//        read arguments
        Object[] args = theJoinPoint.getArgs();

        for (Object tempArgs: args) {
            System.out.println(tempArgs);

            if(tempArgs instanceof Account) {
                Account theAccount = (Account) tempArgs;
                System.out.println("account name: "+ theAccount.getName());
                System.out.println("account level: "+ theAccount.getLevel());

            }
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.niksde.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @AfterReturnn on method: "+ method);
        System.out.println("====>>> Result: "+ result);

//        post process the data ... let's modify
        converAccountNamesToUpperCase(result);

    }

    private void converAccountNamesToUpperCase(List<Account> result) {
        for(Account tempAccount: result) {
            String theUpperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);
        }

        System.out.println("====>>> Result: "+ result);

    }

//    propagate
    @AfterThrowing(
            pointcut = "execution(* com.niksde.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @AfterThrowing on method: "+ method);
        System.out.println("====>>> Exception: "+ theExc);
//        System.out.println("====>>> Result: "+ result);
//
////        post process the data ... let's modify
//        converAccountNamesToUpperCase(result);

    }

    @Around("execution(* com.niksde.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @Around on method: "+ method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception exc) {
            System.out.println("Aspect Exception: "+exc.getMessage());

//            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
            throw exc;
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("====> Duration: "+ duration / 1000.0 + " seconds");

        return result;
    }



}
