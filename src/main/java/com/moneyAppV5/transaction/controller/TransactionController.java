package com.moneyAppV5.transaction.controller;

import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.repository.TransactionRepository;
import com.moneyAppV5.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
class TransactionController
{
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    TransactionRepository repository;
    TransactionService service;

    TransactionController(TransactionService service)
    {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Transaction> createTransaction(@RequestBody @Valid Transaction toCreate) {
        Transaction result = this.service.createTransaction(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping("/gainers")
    ResponseEntity<Gainer> createGainer(@RequestBody @Valid Gainer toCreate) {
        Gainer result = this.service.createGainer(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Transaction>> readAllTransactions() {
        logger.warn("Exposing all the transactions!");
        return ResponseEntity.ok(this.service.readAllTransactions());
    }

    @GetMapping("/{id}")
    ResponseEntity<Transaction> readTransaction(@PathVariable int id) {
        return this.repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTransaction(@PathVariable int id, @RequestBody @Valid Transaction toUpdate) {
        if (!this.service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.service.readTransactionById(id)
                .ifPresent(transaction -> {
                    transaction.updateFrom(toUpdate);
                    this.service.createTransaction(transaction);
                });
        return ResponseEntity.noContent().build();
    }
}
