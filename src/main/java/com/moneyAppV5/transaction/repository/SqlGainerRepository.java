package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Gainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlGainerRepository extends GainerRepository, JpaRepository<Gainer, Integer>
{

}
