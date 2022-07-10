package com.moneyAppV5.product.repository;

import com.moneyAppV5.product.Genre;

import java.util.List;

public interface GenreRepository
{
    List<Genre> findAll();

    Genre save(Genre entity);

    boolean existsByName(String name);
}
