package com.moneyAppV5.transaction;

public enum Role
{
    PAYEE("Kontrahent"),
    GAINER("Beneficjent");

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
