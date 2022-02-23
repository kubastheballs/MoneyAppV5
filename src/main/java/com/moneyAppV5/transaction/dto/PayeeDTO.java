package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;

import javax.persistence.OneToMany;
import java.util.Set;

class PayeeDTO
{
    private String payee;
    private String description;
//    private Set<Transaction> transactions;


    PayeeDTO()
    {
    }

    PayeeDTO(String payee, String description)
    {
        this.payee = payee;
        this.description = description;
    }

    Payee toPayee()
    {
        var result = new Payee();
        result.setPayee(this.payee);
        result.setDescription(this.description);

        return result;
    }
}
