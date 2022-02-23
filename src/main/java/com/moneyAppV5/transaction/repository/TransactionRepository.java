package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository
{
    List<Transaction> findAll();

    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction entity);

    boolean existsById(Integer id);
}
