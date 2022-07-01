package com.moneyAppV5.transaction.repository;

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
//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where TRANSACTION_DATE >= :start and TRANSACTION_DATE <= :end")
//    List<Transaction> findByDates(LocalDate start, LocalDate end);
//
//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where ACCOUNT_ID = :account.id")
//    List<Transaction> getTransactionsByAccount(Account account);

//    TODO tu chyba musi być jakieś złączenie żeby wyciągnąć z tabeli type z category
//    TODO po account.id ma być and i warunek na type
//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where ACCOUNT_ID = :account.id")
//    List<Transaction> getTransactionsByTypeAndAccount(Integer id, Type type);

//    TODO to tylko dla payee występującego w roli gainer
    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where gainer_ID = :id")
    List<Transaction> findByPayeeId(Integer id);

//    @Override
//    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where GAINER_ID = :id")
//    List<Transaction> findByGainerId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS where BUDGET_POSITION_ID = :id")
    List<Transaction> findTransactionsByPositionId(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select * from TRANSACTIONS  inner join (bills inner join budgets on BILLS.BUDGET_ID = BUDGETS.ID) on TRANSACTIONS.BILL_ID = bills.ID  where MONTH = :month and YEAR = :year")
    List<Transaction> findTransactionsByMonthAndYear(int month, int year);

    @Override
    @Query(value = "select * from TRANSACTIONS inner join bills on TRANSACTIONS.BILL_ID = bills.ID where " +
            "ACCOUNT_ID = :accountId", nativeQuery = true)
    List<Transaction> findTransactionsByAccountId(Integer accountId);
//TODO wyniesienie budgetId do billRepo i dodanie stosownej metody w wywołaniu
    @Override
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update TRANSACTIONS set BUDGET_POSITION_ID = :positionId where id = :id")
    void updateBudgetDetailsInTransaction(Integer id, Integer positionId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from transactions inner join categories" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID inner join BILLS on TRANSACTIONS.BILL_ID = Bills.ID " +
            "where main_category_id = :mainId and budget_id = :budgetId")
    double sumActualExpensesByMainCategoryIdAndBudgetId(Integer mainId, Integer budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID  inner join (BILLS inner join budgets on BILLS.BUDGET_ID = BUDGETS.ID)" +
            " on TRANSACTIONS.BILL_ID = Bills.ID where (ACCOUNT_ID = :accountId and " +
            "MONTH = :m and year = :y and TYPE = :type)")
    Optional<Double> sumTransactionsByAccountIdAndMonthAndType(Integer accountId, Integer m, Integer y, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID  inner join BILLS on TRANSACTIONS.BILL_ID = Bills.ID " +
            " where (ACCOUNT_ID = :accountId and TYPE = :type)")
    Optional<Double> sumTransactionsByAccountIdAndType(Integer accountId, String type);

    @Override
    @Query(value = "select sum (amount) from TRANSACTIONS  inner join (BILLS inner join budgets on BILLS.BUDGET_ID = BUDGETS.ID)" +
            " on TRANSACTIONS.BILL_ID = Bills.ID " +
            "where (CATEGORY_ID = :categoryId and MONTH = :month and year = :year)", nativeQuery = true)
    Optional<Double> sumActualMonthTransactionsByCategoryId(Integer categoryId, Integer month, Integer year);

    @Override
    @Query(value = "select sum (amount) from TRANSACTIONS where (CATEGORY_ID = :categoryId)", nativeQuery = true)
    Optional<Double> sumOverallTransactionsByCategoryId(Integer categoryId);

    @Override
    @Query(value = "select sum (amount) from TRANSACTIONS inner join (BILLS inner join budgets on BILLS.BUDGET_ID = BUDGETS.ID) " +
            "on TRANSACTIONS.BILL_ID = Bills.ID  where (Budget_position_ID = :positionId and MONTH = :month and year = :year)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionIdAndMonth(Integer positionId, Integer month, Integer year);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS inner join (BILLS inner join budgets on BILLS.BUDGET_ID = BUDGETS.ID)" +
            " on TRANSACTIONS.BILL_ID = Bills.ID  where (Budget_position_ID = :positionId and MONTH >= :startMonth and year >= :startYear" +
            " and month <= :endMonth and year <= :endYear)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionIdAndDates(Integer positionId, Integer startMonth, Integer startYear, Integer endMonth, Integer endYear);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS  inner join (bills inner join BUDGETS on BILLS.BUDGET_ID = BUDGETS.ID) on TRANSACTIONS.BILL_ID = bills.ID " +
            "where (Budget_position_ID = :positionId and year = :year)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionIdAndYear(Integer positionId, Integer year);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS where (Budget_position_ID = :positionId)", nativeQuery = true)
    Optional<Double> sumTransactionsByPositionId(Integer positionId);

    @Override
    @Query(value="select sum (amount) from TRANSACTIONS inner join bills on TRANSACTIONS.BILL_ID = bills.ID " +
            "where (Budget_ID = :budgetId)", nativeQuery = true)
    Optional<Double> sumTransactionsByBudgetId(Integer budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID  inner join bills on TRANSACTIONS.BILL_ID = bills.ID " +
            "where (BUDGET_ID = :budgetId and " +
            "TYPE = :type)")
    Optional<Double> sumTransactionsByBudgetIdAndType(Integer budgetId, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES" +
            " on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID  inner join bills on TRANSACTIONS.BILL_ID = bills.ID where (BUDGET_ID = :budgetId and " +
            "account_id = :accountId and TYPE = :type)")
    Optional<Double> sumTransactionsByBudgetIdAndAccountIdAndType(Integer budgetId, Integer accountId, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS  inner join bills on TRANSACTIONS.BILL_ID = bills.ID where (BUDGET_position_ID = :positionId and " +
            "day = :day)")
    Optional<Double> sumTransactionsByDayAdnPositionId(Integer day, Integer positionId);

    @Override
    @Query(value = "select * from transactions inner join categories on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where " +
            "main_category_id = :mainCatId", nativeQuery = true)
    List<Transaction> findTransactionsByMainCategoryId(Integer mainCatId);

    @Override
    @Query(value = "select * from transactions inner join categories on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where " +
            "sub_category_id = :subCatId", nativeQuery = true)
    List<Transaction> findTransactionsBySubCategoryId(Integer subCatId);

    @Override
    @Query(value = "select * from transactions inner join categories on TRANSACTIONS.CATEGORY_ID = CATEGORIES.ID where " +
            "type = :t", nativeQuery = true)
    List<Transaction> findTransactionsByTypeName(String t);

    @Override
    @Query(value = "select CATEGORY_ID from transactions where bill_id = :billId", nativeQuery = true)
    List<Integer> findCategoriesIdByBillId(Integer billId);

    @Override
    @Query(value = "select * from transactions inner join BILLS on TRANSACTIONS.bill_ID = bills.ID where " +
            "bills.budget_id = :budgetId", nativeQuery = true)
    List<Transaction> findTransactionsByBudgetId(Integer budgetId);
}
