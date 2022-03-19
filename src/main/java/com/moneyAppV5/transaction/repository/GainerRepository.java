package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;

import java.util.List;
import java.util.Optional;

public interface GainerRepository
{
    List<Gainer> findAll();

    Optional<Gainer> findById(Integer id);
    Gainer findByGainer(String gainer);

    boolean existsById(int id);
    boolean existsByGainer(String name);

    Gainer save(Gainer entity);
}
