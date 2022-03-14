package com.moneyAppV5.transaction.dto;

import com.moneyAppV5.transaction.Gainer;

public class GainerDTO
{
    private String gainer;
    private String description;

    public GainerDTO()
    {
    }

    public GainerDTO(String gainer)
    {
        this.gainer = gainer;
    }

    GainerDTO(String gainer, String description)
    {
        this.gainer = gainer;
        this.description = description;
    }

     public GainerDTO(Gainer gainer)
    {
        this.gainer = gainer.getGainer();
//        this.gainer = gainer.getDescription();
    }

    public Gainer toGainer()
    {
        var result = new Gainer();
        result.setGainer(this.gainer);
        result.setDescription(this.description);

        return result;
    }

    public String getGainer() {
        return gainer;
    }

    public void setGainer(String gainer) {
        this.gainer = gainer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
