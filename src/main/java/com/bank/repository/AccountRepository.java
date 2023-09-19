package com.bank.repository;

import com.bank.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {
    public static List<Account> accountList = new ArrayList<>();
    public Account save(Account account){
        accountList.add(account);
        return account;
    }
}
