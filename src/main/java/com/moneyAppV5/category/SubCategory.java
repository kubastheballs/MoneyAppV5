package com.moneyAppV5.category;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sub_categories")
public
class SubCategory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subCategory;
    private String description;
    @OneToMany(mappedBy = "subCategory")
    private Set<Category> categories;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory(String mainCategory)
    {
        this.subCategory = mainCategory;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    Set<Category> getCategories()
    {
        return categories;
    }

    void setCategories(Set<Category> categories)
    {
        this.categories = categories;
    }
}
