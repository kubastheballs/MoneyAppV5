package com.moneyAppV5.account.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService
{
    AccountRepository repository;

    public AccountService(AccountRepository repository)
    {
        this.repository = repository;
    }

    public Optional<Account> readAccountById(int id)
    {
        return this.repository.findById(id);
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public List<Account> readAllAccounts()
    {
        return this.repository.findAll();
    }

    public Account createAccount(final Account toSave)
    {
        return this.repository.save(toSave);
    }

    public double getActualBalanceByAccount(Account account)
    {
        return this.repository.getAccountActualBalance(account);
    }
}
