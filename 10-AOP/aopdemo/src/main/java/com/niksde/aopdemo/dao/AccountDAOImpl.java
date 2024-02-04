package com.niksde.aopdemo.dao;

import com.niksde.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;
    @Override
    public void addAccount(Account theAccount, boolean vipFLag) {
        System.out.println(getClass()+": DOING MY DB WORK: ADDING AN ACCOUNT ");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass()+ ":Doing work");

        return false;
    }

    @Override
    public String getName() {
        System.out.println(getClass()+ ": getName()");

        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println(getClass()+ ": setName()");

        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println(getClass()+ ": getServiceCode()");

        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+ ": getServiceCode()");

        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if(tripWire) {
            throw new RuntimeException("No account found");
        }
        System.out.println("\n findAccounts Program: "+getClass());
        List<Account> myAccounts = new ArrayList<>();

        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Platinum");
        Account temp3 = new Account("Luca", "Gold");

        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }
}
