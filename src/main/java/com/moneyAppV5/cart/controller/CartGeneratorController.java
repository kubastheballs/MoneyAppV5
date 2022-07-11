package com.moneyAppV5.cart.controller;

import com.moneyAppV5.bill.dto.BillWriteModel;
import com.moneyAppV5.cart.dto.ShoppingListWrapperDTO;
import com.moneyAppV5.cart.dto.ShoppingPositionDTO;
import com.moneyAppV5.transaction.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cartGenerator")
public class CartGeneratorController
{


    @GetMapping
    String getCarGenerator(Model model)
    {
        model.addAttribute("shoppingList", new ShoppingListWrapperDTO());

        return "cartGenerator";
    }

    @PostMapping()
    String generateCarts(@ModelAttribute("shoppingList") @Valid ShoppingListWrapperDTO current, Model model, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("message", "Błędne dane!");

            return "cartGenerator";
        }

//        TODO generowanie wózków zakupowych
//        TODO po wysłaniu formularza lista sie czyści czy zostaje?
        model.addAttribute("shoppingList", current);
        model.addAttribute("message", "Lista utworzona!");

        return "cartGenerator";
    }

    @PostMapping(params = "addPosition")
    String addPositionToList(@ModelAttribute("shoppingList") ShoppingListWrapperDTO current, Model model)
    {
        current.getPositions().add(new ShoppingPositionDTO());

        model.addAttribute("shoppingList", current);

        return "cartGenerator";
    }

    @ModelAttribute("shoppingList")
    ShoppingListWrapperDTO getShoppingList()
    {
        return new ShoppingListWrapperDTO();
    }


}
