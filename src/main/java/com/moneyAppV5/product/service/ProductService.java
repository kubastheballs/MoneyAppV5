package com.moneyAppV5.product.service;

import com.moneyAppV5.product.Genre;
import com.moneyAppV5.product.dto.GenreDTO;
import com.moneyAppV5.product.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService
{
    private final GenreRepository genreRepository;

    public ProductService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> readAllGenresAsDto()
    {
        return this.genreRepository.findAll().stream().map(Genre::toDto).collect(Collectors.toList());
    }

    public void createGenre(GenreDTO current)
    {
        var result = current.toGenre();

        result.setHash(result.hashCode());

        this.genreRepository.save(result);
    }

    public boolean genreExistsInDatabase(String name)
    {
       return this.genreRepository.existsByName(name);
    }
}
