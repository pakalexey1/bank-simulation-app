package com.bank.service.impl;

import com.bank.dto.AccountDTO;
import com.bank.dto.TransactionDTO;
import com.bank.enums.AccountType;
import com.bank.exception.AcountOwnershipException;
import com.bank.exception.BadRequestException;
import com.bank.exception.InsufficientBalanceException;
import com.bank.exception.UnderConstructionException;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class TransactionServiceImpl implements TransactionService {


    @Value("${under_construction}")
    private boolean underConstruction;
    private final AccountRepository accountRepository; //use private final not to forget to add the constructor
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        //if statement to check if the app is under construction
        if(!underConstruction) {

        /*
            -if sender receiver is null?
            -if sender and receiver is the same account?
            -if sender has sufficient funds
            -if both accounts are checking, if not , one of the is saving, it needs to belong to the same userId

         */

            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);
            //makeTransfer

        /*
        after all validations are complete, and money transferred, create Transaction object and save/return it
         */

            TransactionDTO transactionDTO = new TransactionDTO();

            //save into DB and return it (per method request)
            return transactionRepository.save(transactionDTO);
        }else {
            throw new UnderConstructionException("App is under construction, please try again later");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if (checkSenderBalance(sender,amount)){
            //update sender and receiver balance
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new InsufficientBalanceException("Insufficient balance to complete this transfer");
        }
    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        //verify sender has enough balance to send
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;

    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        /*
        create an if statement that checks if one of the account is saving and the user
        of sender or receiver is not the same, throw AccountOwnershipException
         */
        if (sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING)
                && !sender.getUserId().equals(receiver.getUserId())) {
            throw new AcountOwnershipException("If one of the accounts is a saving type, the user ID must be the same for sender and receiver");
        }
    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
        /*
        - if any of the accounts are null
        - if account ids are the same (same account)
        - if the account exists in the database (repository)
         */

        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or Receiver can't be null");
        }

        //if accounts are the same throw BadRequestException with an "accounts need to be different" message
        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Sender account need to be different from the Receiver's account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(Long id) {
        accountRepository.findById(id);
    }


    @Override
    public List<TransactionDTO> findAllTransaction() {

        return transactionRepository.findAll();
    }

    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();

    public TransactionDTO save(TransactionDTO transactionDTO){
        transactionDTOList.add(transactionDTO);
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> last10Transactions() {
        return transactionRepository.findLast10Transactions();
    }

    @Override
    public List<TransactionDTO> findTransactionListById(Long id) {
        return transactionRepository.findTransactionListByAccountId(id);
    }
}
