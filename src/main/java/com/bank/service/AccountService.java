package com.bank.service;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {
    void createNewAccount(AccountDTO accountDTO);
    List<AccountDTO> listAllAccount();

    AccountDTO retrieveByID(Long id);

    void deleteAccount(Long id);

    void activateAccount(Long id);

    List<AccountDTO> listAllActiveAccount();
}
