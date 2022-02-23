package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlPayeeRepository extends PayeeRepository, JpaRepository<Payee, Integer>
{

}
