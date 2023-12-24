package com.bank.service.impl;

import com.bank.entity.Account;
import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import com.bank.dto.AccountDTO;
import com.bank.mapper.AccountMapper;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        accountDTO.setCreationDate(new Date());
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }

    @Override
    public List<AccountDTO> listAllAccount() {
        //the method below retrieves a list of accounts but the method requires a list of Account DTOs
        List<Account> accountList = accountRepository.findAll();

        //convert every element of the list into a DTO, so at the end it's a List of AccountDTOs
        return accountList.stream().map(accountMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AccountDTO retrieveByID(Long id) {
        //find the account entity based on id, convert it to dto and return it
        return accountMapper.convertToDto(accountRepository.findById(id).get());
    }

    @Override
    public void deleteAccount(Long id) {
        //find the account to which the id belongs
        Account account = accountRepository.findById(id).get();

        //set status to deleted
        account.setAccountStatus(AccountStatus.DELETED);

        //save the updated account object
        accountRepository.save(account);
    }

    @Override
    public void activateAccount(Long id) {
        //find the account to which the id belongs
        Account account = accountRepository.findById(id).get();

        //set status to Active
        account.setAccountStatus(AccountStatus.ACTIVE);

        //save the updated account object
        accountRepository.save(account);

    }

    @Override
    public List<AccountDTO> listAllActiveAccount() {

        //need a list of active accounts from repository
        List<Account> accountList = accountRepository.findAllByAccountStatus(AccountStatus.ACTIVE);

        //convert active accounts to accountDTO and return them.
        return accountList.stream().map(accountMapper::convertToDto).collect(Collectors.toList());
    }

}
