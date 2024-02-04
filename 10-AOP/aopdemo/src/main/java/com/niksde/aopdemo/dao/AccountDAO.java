package com.niksde.aopdemo.dao;

import com.niksde.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> findAccounts();
    List<Account> findAccounts(boolean tripWire);
    void addAccount(Account theAccount, boolean vipFLag);

    boolean doWork();

    String getName();

    void setName(String name);

    String getServiceCode();

    void setServiceCode(String serviceCode);



}
