package com.bank.repository;

import com.bank.dto.AccountDTO;
import com.bank.entity.Account;
import com.bank.enums.AccountStatus;
import com.bank.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    List<Account> findAllByAccountStatus(AccountStatus accountStatus);
}
