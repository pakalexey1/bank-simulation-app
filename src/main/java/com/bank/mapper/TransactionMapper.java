package com.bank.mapper;

import com.bank.dto.AccountDTO;
import com.bank.dto.TransactionDTO;
import com.bank.entity.Account;
import com.bank.entity.Transaction;
import org.modelmapper.ModelMapper;

public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDTO convertToDto(Transaction entity){

        return modelMapper.map(entity, TransactionDTO.class);
    }

    public Transaction convertToEntity(TransactionDTO transactionDTO){

        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
