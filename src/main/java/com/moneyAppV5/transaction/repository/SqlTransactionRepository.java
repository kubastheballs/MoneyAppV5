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
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update TRANSACTIONS set BUDGET_POSITION_ID = :positionId, BUDGET_ID = :budgetId where id = :id")
    void updateBudgetDetailsInTransaction(int id, int positionId, int budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from transactions inner join categories where main_category_id = :mainId and budget_id = :budgetId")
    double sumActualExpensesByMainCategoryIdAndBudgetId(int mainId, int budgetId);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES where (ACCOUNT_ID = :accountId and " +
            "MONTH = :m and year = :y and TYPE = :type)")
    Optional<Double> sumTransactionsByAccountIdAndMonthAndType(Integer accountId, Integer m, Integer y, String type);

    @Override
    @Query(nativeQuery = true, value = "select sum (amount) from TRANSACTIONS inner join CATEGORIES where (ACCOUNT_ID = :accountId and " +
            "TYPE = :type)")
    Optional<Double> sumTransactionsByAccountIdAndType(Integer accountId, String type);
}
