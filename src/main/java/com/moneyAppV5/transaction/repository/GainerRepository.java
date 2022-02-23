package com.moneyAppV5.transaction.repository;

import com.moneyAppV5.transaction.Gainer;

import java.util.List;
import java.util.Optional;

public interface GainerRepository
{
    List<Gainer> findAll();

    Optional<Gainer> findById(Integer id);
    boolean existsById(int id);

    Gainer save(Gainer entity);
}
