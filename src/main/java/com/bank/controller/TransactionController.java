package com.bank.controller;

import com.bank.dto.AccountDTO;
import com.bank.dto.TransactionDTO;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
            model.addAttribute("transaction", TransactionDTO.builder().build());
        //provide the list of all accounts
            model.addAttribute("accounts",accountService.listAllAccount());
        //provide the list of the last 10 transactions
            model.addAttribute("lastTransactions",transactionService.last10Transactions());

        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transaction") TransactionDTO transactionDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

            //provide the list of all accounts
            model.addAttribute("accounts",accountService.listAllAccount());
            //provide the list of the last 10 transactions
            model.addAttribute("lastTransactions",transactionService.last10Transactions());
            return "transaction/make-transfer";
        }
        //there is no findById(UID) method so retrieveByID is created to address this issue
        AccountDTO sender = accountService.retrieveByID(transactionDTO.getSender());
        AccountDTO receiver = accountService.retrieveByID(transactionDTO.getReceiver());
        transactionService.makeTransfer(sender,receiver, transactionDTO.getAmount(),new Date(), transactionDTO.getMessage());

        return "redirect:/make-transfer";
    }

    //a method that gets the account id from index.html and prits it on the console
    @GetMapping("transaction/{id}")
    public String getTransactionList(@PathVariable("id") UUID id, Model model){
        System.out.println(id);

        //get teh list of transactions based on id and return as a model attribute

        model.addAttribute("transactions", transactionService.findTransactionListById(id));
        transactionService.findTransactionListById(id);

        return "transaction/transactions";
    }
}
