package com.moneyAppV5.account.service;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.repository.AccountRepository;
import com.moneyAppV5.category.Category;
import com.moneyAppV5.category.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Account createAccount(final AccountDTO toSave)
    {
        return this.repository.save(toSave.toAccount());
    }

    public double getActualBalanceByAccount(Account account)
    {
        return this.repository.getAccountActualBalance(account);
    }

    public List<AccountDTO> readAllAccountsDTO()
    {
        List<AccountDTO> dtos = new ArrayList<>();

        for(Account acc : readAllAccounts())
            dtos.add(new AccountDTO(acc));

        return dtos;
    }

    public void changeBalance(Integer id, double amount)
    {
        this.repository.changeBalance(id, amount);
    }

    public AccountDTO readAccountDtoById(Integer id)
    {
//        TODO czy w orElseThrow należy wrzucić jakiś błąd? - sprawdzić jak jest w todo-app
        return new AccountDTO(this.repository.findById(id).orElseThrow());
    }
}
