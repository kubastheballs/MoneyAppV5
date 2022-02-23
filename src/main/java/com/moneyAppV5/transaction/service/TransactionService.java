package com.moneyAppV5.transaction.service;

import com.moneyAppV5.transaction.Gainer;
import com.moneyAppV5.transaction.Payee;
import com.moneyAppV5.transaction.Transaction;
import com.moneyAppV5.transaction.repository.GainerRepository;
import com.moneyAppV5.transaction.repository.PayeeRepository;
import com.moneyAppV5.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService
{
    private TransactionRepository repository;
    private GainerRepository gainerRepository;
    private PayeeRepository payeeRepository;

    TransactionService(TransactionRepository repository, GainerRepository gainerRepository, PayeeRepository payeeRepository)
    {
        this.repository = repository;
        this.gainerRepository = gainerRepository;
        this.payeeRepository = payeeRepository;
    }

    public boolean existsById(int id)
    {
        return this.repository.existsById(id);
    }

    public Transaction createTransaction(final Transaction toSave)
    {
        return this.repository.save(toSave);
    }

    public Gainer createGainer(final Gainer toSave)
    {
        return this.gainerRepository.save(toSave);
    }

    public Payee createPayee(final Payee toSave)
    {
        return this.payeeRepository.save(toSave);
    }

    public List<Transaction> readAllTransactions()
    {
        return this.repository.findAll();
    }

    public List<Gainer> readAllGainers()
    {
        return this.gainerRepository.findAll();
    }

    public List<Payee> readAllPayees()
    {
        return this.payeeRepository.findAll();
    }

    public Optional<Transaction> readTransactionById(int id)
    {
        return this.repository.findById(id);
    }

    public Optional<Gainer> readGainerById(int id)
    {
        return this.gainerRepository.findById(id);
    }

    public Optional<Payee> readPayeeById(int id)
    {
        return this.payeeRepository.findById(id);
    }

}
