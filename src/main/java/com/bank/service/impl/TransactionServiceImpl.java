package com.bank.service.impl;

import com.bank.exception.BadRequestException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*
            -if sender receiver is null?
            -if sender and receiver is the same account?
            -if sender has sufficient funds
            -if both accounts are checking, if not , one of the is saving, it needs to belong to the same userId

         */

        validateAccount(sender,receiver);

        //makeTransfer


        return null;
    }

    private void validateAccount(Account sender, Account receiver) {
        /*
        - if any of the accounts are null
        - if account ids are the same (same account)
        - if the account exists in the database (repository)
         */

        if (sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver can't be null");
        }
    }


    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
