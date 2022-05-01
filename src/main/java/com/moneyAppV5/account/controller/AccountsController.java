package com.moneyAppV5.account.controller;

import com.moneyAppV5.account.dto.AccountDTO;
import com.moneyAppV5.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/accounts")
class AccountsController
{
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);
    AccountService service;

    AccountsController(AccountService service)
    {
        this.service = service;
    }

    @GetMapping()
    String showAccounts(Model model)
    {
        model.addAttribute("account", new AccountDTO());
        return "accounts";
    }

    @PostMapping()
    String addAccount(@ModelAttribute("account") @Valid AccountDTO current, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
            return "accounts";
//        TODO odświeżenie strony (F5) powoduje ponowne dodanie do bazy jak temu zapobiec?

        if (this.service.existsByName(current.getName()))
        {
            model.addAttribute("message", "Konto o podanej nazwie już istnieje!");

            return "accounts";
        }

        this.service.createAccount(current);
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("accounts", getAccountsDTO());
        model.addAttribute("message", "Dodano konto!");

        return "accounts";
    }

    @ModelAttribute("accounts")
    List<AccountDTO> getAccountsDTO()
    {
        return this.service.readAllAccountsDTO();
    }

    Integer getAccountIdByName()
    {
//        TODO pobieranie name?
        return this.service.readAccountByName("name").getId();
    }

//    @GetMapping(params = {"!sort", "!page", "!size"})
//    ResponseEntity<List<Account>> readAllAccounts() {
//        logger.warn("Exposing all the accounts!");
//        return ResponseEntity.ok(this.service.readAllAccounts());
//    }

//    @PostMapping
//    ResponseEntity<Account> createAccount(@RequestBody @Valid Account toCreate) {
//        Account result = this.service.createAccount(toCreate);
//
//        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
//    }
//
//    @GetMapping("/{id}")
//    ResponseEntity<Account> readAccount(@PathVariable int id) {
//        return this.service.readAccountById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody @Valid Account toUpdate) {
//        if (!this.service.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        this.service.readAccountById(id)
//                .ifPresent(account -> {
//                    account.updateFrom(toUpdate);
//                    this.service.createAccount(account);
//                });
//        return ResponseEntity.noContent().build();
//    }
}
