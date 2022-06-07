package com.moneyAppV5.category.controller;

import com.moneyAppV5.category.dto.CategoryDTO;
import com.moneyAppV5.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
class CategoriesController
{
    private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);
    CategoryService service;


    CategoriesController(CategoryService service)
    {
        this.service = service;
    }

    @GetMapping()
    String showCategories(Model model)
    {
        model.addAttribute("category", new CategoryDTO());

        return "categories";
    }

    @PostMapping()
    String addCategory(@ModelAttribute("category") @Valid CategoryDTO current, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "categories";
        }

//TODO - czy wynieść to do serwisu? ew czy przejść na optionala zamiast if?
        if (this.service.existsInDatabase(current))
        {
            model.addAttribute("message", "Podana kategoria już istnieje!");

            return "categories";
        }

        this.service.createCategory(current);

        model.addAttribute("category", new CategoryDTO());
        model.addAttribute("categories", getCategoriesDto());

        model.addAttribute("message", "Dodano kategorię!");

        return "categories";
    }

    @ModelAttribute("categories")
    List<CategoryDTO> getCategoriesDto()
    {
         return this.service.readAllCategoriesDto();
    }
}
