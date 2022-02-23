package com.moneyAppV5.category;

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
}
