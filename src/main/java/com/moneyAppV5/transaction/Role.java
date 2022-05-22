package com.moneyAppV5.transaction;

public enum Role
{
//    TODO jak to zgrabnie nazwać po polsku? - czy dodać trzecią rolę "płaci mi?" czy zmienić nazwę "płacę mu"
    IS_PAID("Kontrahent"),
    IS_FOR("Beneficjent");

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
