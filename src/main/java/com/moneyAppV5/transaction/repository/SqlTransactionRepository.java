package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.category.Type;
import com.moneyAppV5.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
interface SqlTransactionRepository extends TransactionRepository, JpaRepository<Transaction, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where TRANSACTION_DATE >= :start and TRANSACTION_DATE <= :end")
    List<Transaction> findByDates(LocalDate start, LocalDate end);
//
//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where ACCOUNT_ID = :account.id")
//    List<Transaction> getTransactionsByAccount(Account account);

//    TODO tu chyba musi być jakieś złączenie żeby wyciągnąć z tabeli type z category
//    TODO po account.id ma być and i warunek na type
//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where ACCOUNT_ID = :account.id")
//    List<Transaction> getTransactionsByTypeAndAccount(Integer id, Type type);

    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where IS_PAID_ID = :id or FOR_WHOM_ID = :id")
    List<Transaction> findByPayeeId(Integer id);

//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where GAINER_ID = :id")
//    List<Transaction> findByGainerId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where BUDGET_POSITION_ID = :id")
    List<Transaction> findTransactionsByPositionId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where MONTH = :month and YEAR = :year")
    List<Transaction> findTransactionsByMonthAndYear(int month, int year);

    @Override
    @Query(value = "select * from TRANSACTIONS where ACCOUNT_ID = :accountId", nativeQuery = true)
    List<Transaction> findTransactionsByAccountId(Integer accountId);

    @Override
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update TRANSACTIONS set BUDGET_POSITION_ID = :positionId, BUDGET_ID = :budgetId where id = :id")
    void updateBudgetDetailsInTransaction(int id, int positionId, int budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from transactions inner join categories" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where main_category_id = :mainId and budget_id = :budgetId")
    double sumActualExpensesByMainCategoryIdAndBudgetId(int mainId, int budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where (ACCOUNT_ID = :accountId and " +
            "MONTH = :m and year = :y and TYPE = :type)")
    Optional<Double> sumTransactionsByAccountIdAndMonthAndType(Integer accountId, Integer m, Integer y, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where (ACCOUNT_ID = :accountId and " +
            "TYPE = :type)")
    Optional<Double> sumTransactionsByAccountIdAndType(Integer accountId, String type);

    @Override
    @Query(value = "select sum (amount) from TRANSACTIONS where (CATEGORY_ID = :categoryId and MONTH = :month and year = :year)", nativeQuery = true)
    Optional<Double> sumActualMonthTransactionsByCategoryId(Integer categoryId, Integer month, Integer year);

    @Override
    @Query(value = "select sum (amount) from TRANSACTIONS where (CATEGORY_ID = :categoryId)", nativeQuery = true)
    Optional<Double> sumOverallTransactionsByCategoryId(Integer categoryId);

    @Override
    @Query(value = "select sum (amount) from TRANSACTIONS where (Budget_position_ID = :positionId and MONTH = :month and year = :year)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionIdAndMonth(Integer positionId, Integer month, Integer year);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS where (Budget_position_ID = :positionId and MONTH >= :startMonth and year >= :startYear" +
            " and month <= :endMonth and year <= :endYear)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionIdAndDates(Integer positionId, Integer startMonth, Integer startYear, Integer endMonth, Integer endYear);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS where (Budget_position_ID = :positionId and year = :year)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionIdAndYear(Integer positionId, Integer year);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS where (Budget_position_ID = :positionId)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionId(Integer positionId);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS where (Budget_ID = :budgetId)", nativeQuery = true)
    Optional<Double> sumTransactionsByBudgetId(Integer budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where (BUDGET_ID = :budgetId and " +
            "TYPE = :type)")
    Optional<Double> sumTransactionsByBudgetIdAndType(Integer budgetId, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where (BUDGET_ID = :budgetId and " +
            "account_id = :accountId and TYPE = :type)")
    Optional<Double> sumTransactionsByBudgetIdAndAccountIdAndType(Integer budgetId, Integer accountId, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS where (BUDGET_position_ID = :positionId and " +
            "day = :day)")
    Optional<Double> sumTransactionsByDayAdnPositionId(Integer day, Integer positionId);
}
