package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/budgets")
public class BudgetController
{
    private final BudgetService service;

    public BudgetController(BudgetService service)
    {
        this.service = service;
    }

    @GetMapping()
    String showBudgets(Model model)
    {
//tu bardziej testowe ogólne - konkretne budżety poniżej
//        TODO może tu jako lista budżetów?
        model.addAttribute("message", "Budżet: ");

        model.addAttribute("budget", new BudgetDTO());
        return "budgets";
    }

    @GetMapping("/{id}")
    String showBudgets(Model model, @PathVariable int id)
    {
        var result = this.service.getBudgetDtoById(id);

        model.addAttribute("message", String.format("Budżet: %s %s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);
        model.addAttribute("incomePositions", getIncomePositions(id));

        return "budgets";
    }

    @ModelAttribute("incomePositions")
    List<BudgetPositionDTO> getIncomePositions(int id)
    {
        return this.service.getIncomeBudgetPositionsDtoByBudgetId(id);
    }


















//TODO w sumie chyba jest prościej jednak zrobić te pola w budżecie

//    @ModelAttribute("plannedIncomes")
//    Double getPlannedIncomes()
//    {
////        TODO wyciąganie planowanych dochodów
//        return null;
//    }
//
//    @ModelAttribute("actualIncomes")
//    Double getActualIncomes()
//    {
////        TODO wyciąganie rzeczywistych dochodów
//        return null;
//    }
//
//    @ModelAttribute("incomesBalance")
//    Double getIncomesBalance()
//    {
////        TODO wyciąganie różnicy między dochodem planowanym a rzeczyistym
//        return null;
//    }
//
//    @ModelAttribute("plannedExpenses")
//    Double getPlannedExpenses()
//    {
////        TODO wyciąganie planowanych wydatków
//        return null;
//    }
//
//    @ModelAttribute("actualExpenses")
//    Double getActualExpenses()
//    {
////        TODO wyciąganie rzeczywistych wydatków
//        return null;
//    }
//
//    @ModelAttribute("expensesBalance")
//    Double getExpensesBalance()
//    {
////        TODO wyciąganie różnicy między wydatkami planowanym a rzeczyistym
//        return null;
//    }
}
