package com.moneyAppV5.web;

import com.moneyAppV5.category.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moneys")
class MoneyController
{
    @GetMapping
    String showMoneyApp()
    {
        return "moneys";
    }

//    @GetMapping(path = "/categories")
//    String showCategories(Model model)
//    {
//        model.addAttribute("category", new CategoryDTO());
//        return "categories";
//    }
}
