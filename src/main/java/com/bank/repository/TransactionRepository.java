package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionRepository {
    public static List<Transaction> transactionList = new ArrayList<>();
    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;

    }
}
