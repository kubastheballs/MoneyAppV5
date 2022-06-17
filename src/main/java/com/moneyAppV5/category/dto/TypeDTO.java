package com.moneyAppV5.category.dto;

import com.moneyAppV5.transaction.dto.TransactionDTO;

import java.util.List;

public class TypeDTO
{
    private String type;
    private List<TransactionDTO> transactions;

    private ActualDataWrapperDTO wrapper;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public ActualDataWrapperDTO getWrapper() {
        return wrapper;
    }

    public void setWrapper(ActualDataWrapperDTO wrapper) {
        this.wrapper = wrapper;
    }
}
