package com.moneyAppV5.budget.controller;

import com.moneyAppV5.budget.dto.BudgetPositionDTO;
import com.moneyAppV5.budget.dto.BudgetPositionsWrapperDTO;
import com.moneyAppV5.budget.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    String showBudgetPlan(Model model, @PathVariable Integer hash)
//    String showBudgetPlan(Model model, @PathVariable("hash") Integer hash)
    {
        var budget = this.service.readBudgetByHash(hash);
        var result = this.service.readBudgetPlanAsDto(budget);

        model.addAttribute("budgetHash", hash);
        model.addAttribute("positions", this.service.readPositionsWrapperAsDto(hash));
        model.addAttribute("message", String.format("Planowanie budżetu: %s/%s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);
        model.addAttribute("position", new BudgetPositionDTO());

        return "budgetPlan";
    }



    @PostMapping()
//    String addBudgetPlan(@ModelAttribute("positions") @Valid BudgetPositionsWrapperDTO current, BindingResult bindingResult, Model model, @PathVariable("hash") Integer hash)
    String addBudgetPlan(@ModelAttribute("positions") @Valid BudgetPositionsWrapperDTO current, BindingResult bindingResult, Model model, @PathVariable Integer hash)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");
            System.out.println(bindingResult.getModel());

//         https://stackoverflow.com/questions/49222177/pass-object-in-spring-form-input-hidden-springmvc
//            TODO teraz wywala błędne dane gdy categoryDTO jest rozpisane na elementy składowe jak w ww wątku - jest już ok trzeba było też pomniejsze obiekty rozpisać

//            System.out.println("error");
//            for (BudgetPositionDTO bp : current.getList())
//            {
//                System.out.println(bp.getHash());
//                System.out.println(bp.getCategory());
//                System.out.println(bp.getPlannedAmount());
//            }

            return "budgetPlan";
        }

        var budget = this.service.readBudgetByHash(hash);
        var result = this.service.readBudgetPlanAsDto(budget);

        this.service.updatePlannedAmountInPositions(current);

        model.addAttribute("budgetHash", hash);
        model.addAttribute("positions", current);
        model.addAttribute("message", String.format("Budżet: %s/%s", result.getMonth(), result.getYear()));
        model.addAttribute("budget", result);
//        model.addAttribute("position", new BudgetPositionDTO());

//       TODO dlaczego w planned zwracana jest wartość startowa a nie zaktualizowana? model attribute wczytuje się wcześniej niż zapis do bazy?

        System.out.println();
        System.out.println("controller");
        for (BudgetPositionDTO bp : current.getList()) {
            System.out.println("hash " + bp.getHash());
//            TODO dlaczego category jest null?
            System.out.println("categoryDto " + bp.getCategory());
            System.out.println("category " + bp.getCategoryString());
            System.out.println("planned " + bp.getPlannedAmount());
            System.out.println();
        }

//        TODO powinno zaczytywać zaplanwoane wartości z bazy do wyświetlenia


        return "budgetPlan";
    }


}
