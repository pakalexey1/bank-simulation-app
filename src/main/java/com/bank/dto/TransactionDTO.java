package com.bank.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

//    @NotNull
    private AccountDTO sender;
//    @NotNull
    private AccountDTO receiver;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    @Size(min=2, max =250)
    @Pattern(regexp="^[a-zA-Z0-9]*$") //no special characters validation
    private String message;
    private Date createDate;
}
