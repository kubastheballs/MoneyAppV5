package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.dto.BudgetDTO;
import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/budgetView/{hash}/plan")
class BudgetPlanViewController
{
    private final BudgetService service;

    BudgetPlanViewController(BudgetService service)
    {
        this.service = service;
    }

    @GetMapping
    String showBudgetPlan(Model model, @PathVariable("hash") Integer hash)
    {
        var budget = this.service.readBudgetByHash(hash);
        var result = this.service.readBudgetPlanAsDto(budget);

        model.addAttribute("incomePositions", result.getIncomesDto());
        model.addAttribute("message", String.format("Planowanie budżetu: %s/%s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);
        model.addAttribute("position", new BudgetPositionDTO());

//        model.addAttribute("incomePositions", this.service.readPositionsDtoByBudgetAndType(budget, Type.INCOME));
//        model.addAttribute("expensePositions", this.service.readPositionsDtoByBudgetAndType(budget, Type.EXPENSE));

        return "budgetPlan";
    }



    @PostMapping()
    String addBudgetPlan(@ModelAttribute("positions") @Valid ArrayList<BudgetPositionDTO> current, BindingResult bindingResult, Model model, @PathVariable("hash") Integer hash)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "budgetPlan";
        }

        var budget = this.service.readBudgetByHash(hash);
        var result = this.service.readBudgetPlanAsDto(budget);

        System.out.println("222222");
        System.out.println(current);

//        TODO zapis zaplanowanych wartości do bazy
        this.service.updatePlannedAmountInPositions(current);
//        this.service.updatePlannedAmountInPositions(current);
//        TODO powinno zaczytywać zaplanwoane wartości z bazy do wyświetlenia

//        result.setIncomes(this.service.readPositionsDtoByBudgetAndType(budget, Type.INCOME));
        model.addAttribute("incomePositions", result.getIncomesDto());
        model.addAttribute("message", String.format("Budżet: %s/%s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);
        model.addAttribute("position", new BudgetPositionDTO());

//        model.addAttribute("incomePositions", this.service.readPositionsDtoByBudgetAndType(budget, Type.INCOME));
//        model.addAttribute("expensePositions", this.service.readPositionsDtoByBudgetAndType(budget, Type.EXPENSE));

        return "budgetPlan";
    }

//    @PostMapping(params = "xxx")
//    String savePlannedAmounts(@ModelAttribute("budget") BudgetDTO current)
//    {
//        current.getIncomesDto();
//
//        return "budgetPlan";
//    }
}
