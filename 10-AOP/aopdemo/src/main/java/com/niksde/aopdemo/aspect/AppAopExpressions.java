package com.niksde.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // optional for point cut expressions
public class AppAopExpressions {
    //    pointcut expression declaration
    @Pointcut("execution(* com.niksde.aopdemo.dao.*.*(..))")
    public void forDaoPage() {}

    @Pointcut("execution(* com.niksde.aopdemo.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.niksde.aopdemo.dao.*.set*(..))")
    public void setter() {}

    @Pointcut("forDaoPage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

//    @Before("execution(public void com.niksde.aopdemo.dao.AccountDAO.addAccount())")
//@Before("execution(public void add*())")
//@Before("execution(* add*())")
//@Before("execution(* add*(com.niksde.aopdemo.Account))")
//@Before("execution(* add*(com.niksde.aopdemo.Account, boolean))")
//@Before("execution(* add*(com.niksde.aopdemo.Account, ..))")
//@Before("execution(* add*(com.niksde.aopdemo.Account, *))")
//@Before("execution(* add*(..))")
//@Before("execution(* com.niksde.aopdemo.dao.*.*(..))")
//@Before("forDaoPage()")
}
