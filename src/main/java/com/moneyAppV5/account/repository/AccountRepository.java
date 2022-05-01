package com.moneyAppV5.account.repository;

import com.moneyAppV5.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository
{
    List<Account> findAll();

    Optional<Account> findById(Integer id);
    Account findByName(String name);

    boolean existsById(int id);

    Account save(Account entity);

    double getAccountActualBalance(Account account);

    void changeBalance(Integer id, double amount);


    Optional<Account> findByHash(Integer hash);

    boolean existsByName(String name);
}
