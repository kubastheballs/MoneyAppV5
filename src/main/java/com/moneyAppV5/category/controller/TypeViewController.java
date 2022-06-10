package com.moneyAppV5.category.controller;

import com.moneyAppV5.category.service.CategoryService;
import com.moneyAppV5.utils.UtilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typeView/{hash}")
public class TypeViewController
{
    private final CategoryService service;
    private final UtilService utilService;

    public TypeViewController(CategoryService service, UtilService utilService)
    {
        this.service = service;
        this.utilService = utilService;
    }

    @GetMapping
    String showTypeView(Model model, @PathVariable Integer hash)
    {
//        TODO - typeDTO

        return "typeView";
    }
}
