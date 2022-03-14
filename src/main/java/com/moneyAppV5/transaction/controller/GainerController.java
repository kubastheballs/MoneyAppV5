package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.transaction.dto.GainerDTO;
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
@RequestMapping("/gainers")
public class GainerController
{
        TransactionService service;

        public GainerController(TransactionService service) {
            this.service = service;
        }

        @GetMapping()
        String showGainers(Model model)
        {
            model.addAttribute("gainer", new GainerDTO());
            return "gainers";
        }

        @PostMapping()
        String addGainer(@ModelAttribute("gainer") @Valid GainerDTO current, BindingResult bindingResult, Model model)
        {
            if (bindingResult.hasErrors())
                return "gainers";
//        TODO odświeżenie strony (F5) powoduje ponowne dodanie do bazy jak temu zapobiec?

            this.service.createGainer(current);
            model.addAttribute("gainer", new GainerDTO());
            model.addAttribute("gainers", getGainersDTO());
            model.addAttribute("message", "Dodano beneficjenta!");

            return "gainers";
        }

        @ModelAttribute("gainers")
        List<GainerDTO> getGainersDTO()
        {
            return this.service.readAllGainersDto();
        }
}
