package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;

import javax.persistence.OneToMany;
import java.util.Set;

public class GainerDTO
{
    private String gainer;
    private String description;

    GainerDTO()
    {
    }

    GainerDTO(String gainer, String description)
    {
        this.gainer = gainer;
        this.description = description;
    }

    public Gainer toGainer()
    {
        var result = new Gainer();
        result.setGainer(this.gainer);
        result.setDescription(this.description);

        return result;
    }
}
