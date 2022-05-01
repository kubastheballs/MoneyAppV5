package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;

import java.util.List;
import java.util.Set;

public class PayeeDTO
{
    private String name;
    private Role role;
    private String description;
    private List<TransactionDTO> transactionsDto;
    private Integer hash;


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


     public PayeeDTO(Payee payee)
    {
        this.name = payee.getName();
        this.role = payee.getRole();
        this.description = payee.getDescription();
        this.hash = payee.getHash();
//        TODO w payee transakcje są rozdzielone na dla i od (zapewne chodzi o to że kontrahent mozę byc obustronny) co z tym zrobić\
//        TODO spiąć do pojedynczej listy? i wtedy kontrahent ma sztywno przypisaną rolę?
//        this.transactionsDto = transactionsToDto(payee.getT)
    }

    public Payee toPayee()
    {
        var result = new Payee();
        result.setName(this.name);
        result.setRole(this.role);
        result.setDescription(this.description);
        result.setHash(result.hashCode());

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

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public List<TransactionDTO> getTransactionsDto() {
        return transactionsDto;
    }

    public void setTransactionsDto(List<TransactionDTO> transactionsDto) {
        this.transactionsDto = transactionsDto;
    }
}
