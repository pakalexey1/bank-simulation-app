package com.bank.entity;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate creationDate;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
