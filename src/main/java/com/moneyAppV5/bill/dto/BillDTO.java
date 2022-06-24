package com.moneyAppV5.bill.dto;

import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;

public class BillDTO
{
    private int day;
    private PayeeDTO payee;
    private AccountDTO account;
    private BudgetDTO budget;
    private List<TransactionDTO> transactions;
    private int hash;
}
