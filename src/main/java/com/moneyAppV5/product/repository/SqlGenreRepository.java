package com.moneyAppV5.product.repository;

import com.moneyAppV5.product.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlGenreRepository extends GenreRepository, JpaRepository<Genre, Integer>
{
}
