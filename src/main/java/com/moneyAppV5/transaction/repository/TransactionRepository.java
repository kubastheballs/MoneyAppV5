package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository
{
    List<Transaction> findAll();
    List<Transaction> findByDates(LocalDate start, LocalDate end);
    List<Transaction> findByPayee(Integer id);
    List<Transaction> findByGainer(Integer id);

    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction entity);

    boolean existsById(Integer id);
}
