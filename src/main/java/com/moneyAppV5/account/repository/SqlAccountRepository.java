package com.moneyAppV5.account.repository;

import com.moneyAppV5.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
interface SqlAccountRepository extends AccountRepository, JpaRepository<Account, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select ACTUAL_BALANCE from ACCOUNTS WHERE ACCOUNTS.ID = :account.id")
    double getAccountActualBalance(Account account);

    @Override
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update ACCOUNTS set ACTUAL_BALANCE = ACTUAL_BALANCE + :amount where ID = :id")
    void changeBalance(Integer id, double amount);
}
