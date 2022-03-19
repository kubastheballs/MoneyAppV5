package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlPayeeRepository extends PayeeRepository, JpaRepository<Payee, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from PAYEES where PAYEE = :name")
    Optional<Payee> findByName(String name);

//    @Override
//    @Query(nativeQuery = true, value = "select * from PAYEES where PAYEE = :name")
//    boolean existsByName(String name);
}
