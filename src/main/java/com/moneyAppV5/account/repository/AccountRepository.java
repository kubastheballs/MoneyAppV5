package com.moneyAppV5.account.repository;

import com.moneyAppV5.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository
{
    List<Account> findAll();

    Optional<Account> findById(Integer id);
    boolean existsById(int id);

    Account save(Account entity);

    double getAccountActualBalance(Account account);
}
