package com.bank;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {

        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);
        //get account and transaction service beans
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);
//
//        //create 2 accounts sender and receiver
//        AccountDTO sender = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING,1L);
//        AccountDTO receiver = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.CHECKING, 2L);
//        AccountDTO receiver2 = accountService.createNewAccount(BigDecimal.valueOf(7050), new Date(), AccountType.CHECKING, 123L);
//        AccountDTO receiver3 = accountService.createNewAccount(BigDecimal.valueOf(5055), new Date(), AccountType.SAVING, 124L);
//
//        accountService.listAllAccount().forEach(System.out::println);
//
//        transactionService.makeTransfer(sender, receiver, new BigDecimal(40), new Date(),"Transaction 1");
//
//        System.out.println(transactionService.findAllTransaction().get(0));
//
//        accountService.listAllAccount().forEach(System.out::println);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
