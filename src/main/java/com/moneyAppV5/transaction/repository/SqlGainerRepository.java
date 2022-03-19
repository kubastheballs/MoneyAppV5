package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Gainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlGainerRepository extends GainerRepository, JpaRepository<Gainer, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select * from GAINERS where GAINER = :gainer")
    Gainer findByGainer(String gainer);
}
