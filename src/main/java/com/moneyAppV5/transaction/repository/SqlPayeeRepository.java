package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlPayeeRepository extends PayeeRepository, JpaRepository<Payee, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from PAYEES where name = :name")
    Optional<Payee> findByName(@Param("name")String name);

    @Override
    @Query(nativeQuery = true, value = "select * from payees where role = :role")
    List<Payee> findPayeesByRole(@Param("role") String role);

//    @Override
//    @Query(nativeQuery = true, value = "select * from PAYEES where PAYEE = :name")
//    boolean existsByName(String name);
}
