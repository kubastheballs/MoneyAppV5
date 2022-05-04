package com.moneyAppV5.category.controller;

import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.category.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/categoryView/{hash}")
public class CategoryViewController
{
    private final CategoryService service;

    public CategoryViewController(CategoryService service)
    {
        this.service = service;
    }

    @GetMapping
    String showCategoryView(@PathVariable Integer hash, Model model)
    {
        var category = this.service.readCategoryByHash(hash);
        var result = new CategoryDTO(category);

        model.addAttribute("message", String.format("Kategoria: %s", result.getCategory()));
        model.addAttribute("category", result);

        Integer month = LocalDate.now().getMonthValue();
        Integer year = LocalDate.now().getYear();

        model.addAttribute("actualMonthSum", this.service.sumTransactionsByActualMonth(category, month, year));
        model.addAttribute("overallSum", this.service.sumOverallTransactions(category));

        return "categoryView";
    }
}
