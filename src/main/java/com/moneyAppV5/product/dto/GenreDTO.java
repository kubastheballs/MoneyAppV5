package com.moneyAppV5.product.dto;

import com.moneyAppV5.product.Genre;

public class GenreDTO
{
    private String name;
    private String description;
    private int hash;

    public GenreDTO()
    {
    }

    public GenreDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public GenreDTO(Genre entity)
    {
        this.name = entity.getName();
        this.description = entity.getDescription();
    }

    public Genre toGenre()
    {
       var result = new Genre();

        result.setName(this.name);
        result.setDescription(this.description);
        result.setHash(this.hash);

       return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}
