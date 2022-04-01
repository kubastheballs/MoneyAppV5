package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;

import java.util.List;
import java.util.Optional;

public interface PayeeRepository
{
    List<Payee> findAll();

    Optional<Payee> findById(Integer id);
    Optional<Payee> findByName(String name);

    boolean existsById(int id);
    boolean existsByName(String name);

    Payee save(Payee entity);

    Payee findByNameAndRole(String name, Role isFor);

    List<Payee> findPayeesByRole(String role);
}
