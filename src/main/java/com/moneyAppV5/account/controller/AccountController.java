package com.moneyAppV5.account.controller;

import com.moneyAppV5.account.Account;
import com.moneyAppV5.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/accounts")
class AccountController
{
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    AccountService service;

    AccountController(AccountService service)
    {
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Account>> readAllAccounts() {
        logger.warn("Exposing all the accounts!");
        return ResponseEntity.ok(this.service.readAllAccounts());
    }

    @PostMapping
    ResponseEntity<Account> createAccount(@RequestBody @Valid Account toCreate) {
        Account result = this.service.createAccount(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/{id}")
    ResponseEntity<Account> readAccount(@PathVariable int id) {
        return this.service.readAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody @Valid Account toUpdate) {
        if (!this.service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.service.readAccountById(id)
                .ifPresent(account -> {
                    account.updateFrom(toUpdate);
                    this.service.createAccount(account);
                });
        return ResponseEntity.noContent().build();
    }
}
