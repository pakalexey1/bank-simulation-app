package com.bank.service.impl;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import com.bank.dto.AccountDTO;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {
        //create Account object
        AccountDTO accountDTO = AccountDTO.builder().id(UUID.randomUUID()).userId(userId)
                .balance(balance).accountType(accountType).creationDate(createDate).accountStatus(AccountStatus.ACTIVE).build();
        //save it into the database (repository)
        //return the created object
        return accountRepository.save(accountDTO);
    }

    @Override
    public List<AccountDTO> listAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public AccountDTO retrieveByID(UUID id) {
        return accountRepository.findById(id);
    }

}
