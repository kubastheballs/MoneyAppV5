package com.moneyAppV5.transaction;

public enum Role
{
//    TODO jak to zgrabnie nazwać po polsku?
    IS_PAID("Płacę mu"),
    IS_FOR("Dla niego");


    private String name;

    Role(String name)
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
