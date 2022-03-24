package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;

public class PayeeDTO
{
    private String name;
    private Role role;
    private String description;
//    private Set<Transaction> transactions;


    public PayeeDTO()
    {
    }

    public PayeeDTO(String name, Role role)
    {
        this.name = name;
        this.role = role;
    }

    PayeeDTO(String name, Role role, String description)
    {
        this.name = name;
        this.role = role;
        this.description = description;
    }

     public PayeeDTO(Payee name)
    {
        this.name = name.getName();
        this.role = name.getRole();
        this.description = name.getDescription();
    }

    public Payee toPayee()
    {
        var result = new Payee();
        result.setName(this.name);
        result.setRole(this.role);
        result.setDescription(this.description);

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
