package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }


    @GetMapping("/make-transfer")
    public String makeTransfer(Model model){

        //provide empty transaction object
            model.addAttribute("transaction", Transaction.builder().build());
        //provide the list of all accounts
            model.addAttribute("accounts",accountService.listAllAccount());
        //provide the list of the last 10 transactions
            model.addAttribute("lastTransactions",transactionService.last10Transactions());

        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@ModelAttribute("transaction") Transaction transaction){

        //there is no findById(UID) method so retrieveByID is created to address this issue
        Account sender = accountService.retreieveByID(transaction.getSender());
        Account receiver = accountService.retreieveByID(transaction.getReceiver());
        transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }

    //a method that gets the account id from index.html and prits it on the console
    @GetMapping("transaction/{id}")
    public String getTransactionList(@PathVariable("id") UUID id){
        System.out.println(id);

        return "transaction/transactions";
    }
}
