package com.moneyAppV5.cart.controller;

import com.moneyAppV5.cart.dto.ShoppingListWrapperDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CartGeneratorController
{


    @ModelAttribute("shoppingList")
    ShoppingListWrapperDTO getShoppingList()
    {
        return new ShoppingListWrapperDTO();
    }


}
