package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.transaction.Payee;

import java.util.List;
import java.util.Optional;

public interface PayeeRepository
{
    List<Payee> findAll();

    Optional<Payee> findById(Integer id);
    Optional<Payee> findByName(String name);

    boolean existsById(int id);
    boolean existsByPayee(String name);

    Payee save(Payee entity);
}
