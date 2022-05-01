package com.moneyAppV5.category;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "main_categories")
public class MainCategory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mainCategory;
////    private String description;
    @OneToMany(mappedBy = "mainCategory")
    private Set<Category> categories;
    @OneToMany(mappedBy = "subCategory")
    private Set<SubCategory> subCategories;
    private Integer hash;

     public MainCategory()
    {
    }

    public MainCategory(String mainCategory)
    {
        this.mainCategory = mainCategory;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.mainCategory);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMainCategory()
    {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory)
    {
        this.mainCategory = mainCategory;
    }
//
//    public String getDescription()
//    {
//        return description;
//    }
//
//    public void setDescription(String description)
//    {
//        this.description = description;
//    }


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}
