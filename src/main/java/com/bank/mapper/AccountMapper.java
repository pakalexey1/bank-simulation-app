package com.bank.mapper;

import com.bank.dto.AccountDTO;
import com.bank.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO convertToDto(Account entity){

        return modelMapper.map(entity, AccountDTO.class);
    }

    public Account convertToEntity(Account accountDto){

        return modelMapper.map(accountDto, Account.class);
    }
}
