package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.dto.TransactionDTO;

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
    List<Transaction> findTransactionsByBudgetId(Integer id);
    List<Transaction> findTransactionsByAccountId(Integer accountId);
    List<Transaction> findTransactionsByMainCategoryId(Integer mainCatId);


    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction entity);

    boolean existsById(Integer id);

    void updateBudgetDetailsInTransaction(int id, int positionId, int budgetId);

    double sumActualExpensesByMainCategoryIdAndBudgetId(int mainId, int budgetId);

    Optional<Double> sumTransactionsByAccountIdAndMonthAndType(Integer accountId, Integer m, Integer y, String type);
    Optional<Double> sumTransactionsByAccountIdAndType(Integer accountId, String type);
    Optional<Double> sumActualMonthTransactionsByCategoryId(Integer categoryId, Integer month, Integer year);
    Optional<Double> sumOverallTransactionsByCategoryId(Integer categoryId);
    Optional<Double> sumTransactionsByPositionId(Integer positionId);
    Optional<Double> sumTransactionsByPositionIdAndDates(Integer positionId, Integer startMonth, Integer startYear, Integer endMonth, Integer endYear);
    Optional<Double> sumTransactionsByPositionIdAndMonth(Integer positionId, Integer month, Integer year);
    Optional<Double> sumTransactionsByBudgetIdAndAccountIdAndType(Integer budgetId, Integer accountId, String type);

    Optional<Double> sumTransactionsByPositionIdAndYear(Integer positionId, Integer year);

    Optional<Double> sumTransactionsByBudgetId(Integer budgetId);
    Optional<Double> sumTransactionsByBudgetIdAndType(Integer budgetId, String type);


    Optional<Double> sumTransactionsByDayAdnPositionId(Integer day, Integer positionId);


    List<Transaction> findTransactionsBySubCategoryId(Integer subCatId);

    List<Transaction> findTransactionsByTypeName(String t);
}
