package com.moneyAppV5.budget.dto;

import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.time.LocalDate;
import java.util.List;

public class DayDTO
{
    private String date;
    private BudgetDTO budgetDTO;
    private List<TransactionDTO> transactions;
}
