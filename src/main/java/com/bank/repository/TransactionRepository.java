package com.bank.repository;

import com.bank.dto.TransactionDTO;
import com.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
//    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();
//    public TransactionDTO save(TransactionDTO transactionDTO){
//        transactionDTOList.add(transactionDTO);
//        return transactionDTO;
//    }
//
//
//    public List<TransactionDTO> findAll() {
//
//        return transactionDTOList;
//    }
//
//    public List<TransactionDTO> findLast10Transactions() {
//        return transactionDTOList.stream()
//                .sorted(Comparator.comparing(TransactionDTO::getCreateDate).reversed())
//                .limit(10)
//                .collect(Collectors.toList());
//    }
//
//    public List<TransactionDTO> findTransactionListByAccountId(Long id) {
//        //if account id is used either as a sender or receiver, return those transactions
//        return transactionDTOList.stream()
//                .filter(transactionDTO -> transactionDTO.getSender().equals(id) || transactionDTO.getReceiver().equals(id))
//                .collect(Collectors.toList());
//    }
}
