package com.moneyAppV5.category.dto;

import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;

public class TypeDTO
{
    private String type;
    private List<TransactionDTO> transactions;
//    TODO type nie ma hasha
    private int hash;

    private double actualMonthSum;
    private double actualMonthMinusOneSum;
    private double actualMonthMinusTwoSum;
    private double actualYearSum;
    private double overallSum;

    private int actualMonthCount;
    private int actualMonthMinusOneCount;
    private int actualMonthMinusTwoCount;
    private int actualYearCount;
    private int overallCount;


}
