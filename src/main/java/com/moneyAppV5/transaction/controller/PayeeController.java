package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.service.TransactionService;
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
@RequestMapping("/payees")
public class PayeeController
{
        TransactionService service;

    public PayeeController(TransactionService service) {
        this.service = service;
    }

    @GetMapping()
    String showPayees(Model model)
    {
        model.addAttribute("payee", new PayeeDTO());
        return "payees";
    }

    @PostMapping()
    String addPayee(@ModelAttribute("payee") @Valid PayeeDTO current, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
            return "payees";
//        TODO odświeżenie strony (F5) powoduje ponowne dodanie do bazy jak temu zapobiec?

        this.service.createPayee(current);
        System.out.println(current.getName());
        System.out.println(current.getRole());
        model.addAttribute("payee", new PayeeDTO());
        model.addAttribute("payees", getPayeesDTO());
        model.addAttribute("message", "Dodano kontrahenta!");

        return "payees";
    }

    @ModelAttribute("payees")
    List<PayeeDTO> getPayeesDTO()
    {
        return this.service.readAllPayeesDto();
    }
}
