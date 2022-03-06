package com.moneyAppV5.category;

import javax.persistence.*;


public enum Type
{
    INCOME("Dochód"),
    EXPENSE("Wydatek"),
    INTERNAL("Transfer wewnętrzny");

    private String name;

    Type(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
