package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;

import javax.persistence.OneToMany;
import java.util.Set;

public class PayeeDTO
{
    private String payee;
    private String description;
//    private Set<Transaction> transactions;


    public PayeeDTO()
    {
    }

    public PayeeDTO(String payee)
    {
        this.payee = payee;
    }

    PayeeDTO(String payee, String description)
    {
        this.payee = payee;
        this.description = description;
    }

     public PayeeDTO(Payee payee)
    {
        this.payee = payee.getPayee();
        this.description = payee.getDescription();
    }

    public Payee toPayee()
    {
        var result = new Payee();
        result.setPayee(this.payee);
        result.setDescription(this.description);

        return result;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
