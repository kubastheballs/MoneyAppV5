package com.moneyAppV5.category;

import com.moneyAppV5.category.dto.MainCategoryDTO;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "main_categories")
public class MainCategory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mainCategory;
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

    public MainCategoryDTO toDto()
    {
        var main = new MainCategoryDTO();

        main.setMainCategory(this.mainCategory);
        main.setSubCategories(this.subCategories.stream().map(SubCategory::toDto).collect(Collectors.toList()));
        main.setHash(this.hash);

        return main;
    }
}
