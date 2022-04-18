package com.moneyAppV5.web;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;

@Controller
@RequestMapping("/moneys")
class MoneysController
{
    private final BudgetService budgetService;

    public MoneysController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    String showMoneyApp(Model model)
    {
        model.addAttribute("budget", new BudgetDTO());
//        TODO - jeśli nie ma budżetu na aktualny miesiąc to przenosi do stworzenia? jeśli nie ma to póki co wywala null Pointer
//        model.addAttribute("budgetId", this.budgetService.readBudgetByMonthAndYear(LocalDate.now().getMonthValue(), LocalDate.now().getYear()).getId() );

        return "moneys";
    }

//    @ModelAttribute("budgetId")
//    void getBudgetIdByActualMonthAndYear(Model model)
//    {
//        int m = LocalDate.now().getMonthValue();
//        int y = LocalDate.now().getYear();
//
//        var budget = budgetService.readBudgetByMonthAndYear(m, y);
//
//        model.addAttribute("budgetId")
//
//    }
//  TODO przeniesione do budgets
//    @PostMapping()
//    String addBudget(@ModelAttribute("budgetDto") @Valid BudgetDTO current, BindingResult bindingResult, Model model)
//    {
//        if (bindingResult.hasErrors())
//        {
//            model.addAttribute("message", "Błędne dane!");
//
//            return "moneys";
//        }
//
//        var budget = this.budgetService.createBudget(current);
//
//        this.budgetService.createPositionsListByBudget(budget);
//
//        model.addAttribute("budget", new BudgetDTO());
//        model.addAttribute("message", "Dodano budżet!");
//
////        return "redirect:/budgets.html/" + budget.getId();
//        return "moneys";
//    }


//    @GetMapping(path = "/categories")
//    String showCategories(Model model)
//    {
//        model.addAttribute("category", new CategoryDTO());
//        return "categories";
//    }
}
