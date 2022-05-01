package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.transaction.dto.PayeeDTO;
import com.moneyAppV5.transaction.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payeeView/{hash}")
public class PayeeViewController
{
    private final TransactionService service;

    public PayeeViewController(TransactionService service)
    {
        this.service = service;
    }

    @GetMapping
    String showPayeeView(Model model, @PathVariable Integer hash)
    {
        var payee = this.service.readPayeeByHash(hash);
        var result = new PayeeDTO(payee);

        model.addAttribute("payee", result);
        model.addAttribute("message", String.format("Kontrahent: %s", result.getName()));

//        TODO lista transakcji - czy z obiektu czy z bazy?
//        lista transakcji z bazy ze względu na podział w obiekcie - przynajmniej na razie
        model.addAttribute("transactions", this.service.readTransactionsDtoByPayeeId(payee.getId()));


        return "payeeView";
    }
}
