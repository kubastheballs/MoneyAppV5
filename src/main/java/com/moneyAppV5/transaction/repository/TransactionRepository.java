package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository
{
    List<Transaction> findAll();
    List<Transaction> findByDates(LocalDate start, LocalDate end);
    List<Transaction> findByPayeeId(Integer id);
//    List<Transaction> findByGainerId(Integer id);
    List<Transaction> findTransactionsByPositionId(Integer id);
    List<Transaction> findTransactionsByMonthAndYear(int month, int year);
    List<Transaction> findTransactionsByBudgetId(int id);

    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction entity);

    boolean existsById(Integer id);

    void updateBudgetDetailsInTransaction(int id, int positionId, int budgetId);

    double sumActualExpensesByMainCategoryIdAndBudgetId(int mainId, int budgetId);

    Optional<Double> sumTransactionsByAccountIdAndMonthAndType(Integer id, Integer m, Integer y, String type);
    Optional<Double> sumTransactionsByAccountIdAndType(Integer accountId, String type);
}
